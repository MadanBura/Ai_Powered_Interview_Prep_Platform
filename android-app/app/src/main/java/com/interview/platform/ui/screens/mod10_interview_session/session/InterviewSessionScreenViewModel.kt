package com.interview.platform.ui.screens.mod10_interview_session.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.platform.data.remote.api.Mod10InterviewSessionApiService
import com.interview.platform.data.remote.api.Mod11QuestionDeliveryApiService
import com.interview.platform.data.remote.dto.mod10_interview_session.CompleteSessionRequestDto
import com.interview.platform.data.remote.dto.mod11_question_delivery.QuestionItemDto
import com.interview.platform.data.repository.InterviewSessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InterviewSessionScreenViewModel @Inject constructor(
    private val questionApi: Mod11QuestionDeliveryApiService,
    private val sessionApi: Mod10InterviewSessionApiService,
    private val sessionManager: InterviewSessionManager
) : ViewModel() {
    private val _uiState = MutableStateFlow<InterviewSessionScreenUiState>(InterviewSessionScreenUiState.Loading)
    val uiState: StateFlow<InterviewSessionScreenUiState> = _uiState.asStateFlow()
    
    init {
        loadQuestions()
    }

    private fun loadQuestions() {
        val sessionId = sessionManager.currentSessionId
        if (sessionId == null) {
            _uiState.value = InterviewSessionScreenUiState.Error("No active session found")
            return
        }

        viewModelScope.launch {
            try {
                val response = questionApi.retrieveQuestions(sessionId)
                if (response.isSuccessful) {
                    val questions = response.body()?.data ?: emptyList()
                    sessionManager.sessionQuestions = questions.take(sessionManager.questionCount)
                    sessionManager.currentQuestionIndex = 0
                    updateUiStateForCurrentQuestion()
                    startTimer()
                } else {
                    _uiState.value = InterviewSessionScreenUiState.Error("Failed to fetch questions: ${response.code()}")
                }
            } catch (e: Exception) {
                _uiState.value = InterviewSessionScreenUiState.Error("Network error: ${e.message}")
            }
        }
    }
    
    fun updateUiStateForCurrentQuestion() {
        val currentQ = sessionManager.currentQuestion
        if (currentQ != null) {
            _uiState.value = InterviewSessionScreenUiState.Success(
                currentQuestionIndex = sessionManager.currentQuestionIndex + 1,
                totalQuestions = sessionManager.sessionQuestions.size,
                questionText = currentQ.text,
                topic = "Behavioral: \"Conflict Resolution\"",
                remainingTimeSeconds = (currentQ.expectedDurationMinutes ?: 5) * 60,
                totalTimeSeconds = (currentQ.expectedDurationMinutes ?: 5) * 60,
                phase = SessionPhase.IDLE,
                recordingTimeSeconds = 0,
                answerText = ""
            )
        } else {
            completeSession()
        }
    }

    fun skipQuestion() {
        val nextIdx = sessionManager.currentQuestionIndex + 1
        sessionManager.currentQuestionIndex = nextIdx
        updateUiStateForCurrentQuestion()
    }
    
    fun toggleTypingMode() {
        val currentState = _uiState.value as? InterviewSessionScreenUiState.Success ?: return
        if (currentState.phase == SessionPhase.IDLE) {
            _uiState.value = currentState.copy(phase = SessionPhase.REVIEW, answerText = "")
        } else if (currentState.phase == SessionPhase.REVIEW) {
            _uiState.value = currentState.copy(phase = SessionPhase.IDLE)
        }
    }

    fun updateAnswerText(text: String) {
        val currentState = _uiState.value as? InterviewSessionScreenUiState.Success ?: return
        _uiState.value = currentState.copy(answerText = text)
    }

    fun startRecording() {
        val currentState = _uiState.value as? InterviewSessionScreenUiState.Success ?: return
        _uiState.value = currentState.copy(
            phase = SessionPhase.RECORDING,
            recordingTimeSeconds = 0
        )
    }

    fun stopRecording() {
        val currentState = _uiState.value as? InterviewSessionScreenUiState.Success ?: return
        
        _uiState.value = currentState.copy(
            phase = SessionPhase.REVIEW
        )
    }

    fun appendPartialTranscript(text: String) {
        val currentState = _uiState.value as? InterviewSessionScreenUiState.Success ?: return
        if (currentState.phase == SessionPhase.RECORDING) {
            _uiState.value = currentState.copy(answerText = text)
        }
    }

    fun setFinalTranscript(text: String) {
        val currentState = _uiState.value as? InterviewSessionScreenUiState.Success ?: return
        _uiState.value = currentState.copy(
            phase = SessionPhase.REVIEW,
            answerText = text
        )
    }

    fun reRecord() {
        val currentState = _uiState.value as? InterviewSessionScreenUiState.Success ?: return
        _uiState.value = currentState.copy(
            phase = SessionPhase.RECORDING,
            recordingTimeSeconds = 0,
            answerText = ""
        )
    }

    fun submitAnswer() {
        val currentState = _uiState.value as? InterviewSessionScreenUiState.Success ?: return
        val currentQ = sessionManager.currentQuestion ?: return
        val sessionId = sessionManager.currentSessionId ?: return
        
        viewModelScope.launch {
            try {
                // Submit answer to backend
                val req = com.interview.platform.data.remote.dto.mod10_interview_session.SubmitAnswerRequestDto(
                    questionId = currentQ.id,
                    answerText = currentState.answerText
                )
                sessionApi.submitAnswer(sessionId, req)
            } catch (e: Exception) {
                // Handle error or ignore for now
            }
            
            // Move to next question
            val nextIdx = sessionManager.currentQuestionIndex + 1
            sessionManager.currentQuestionIndex = nextIdx
            updateUiStateForCurrentQuestion()
        }
    }
    
    private fun completeSession() {
        _uiState.value = InterviewSessionScreenUiState.Loading
        viewModelScope.launch {
            try {
                sessionManager.currentSessionId?.let { sessionId ->
                    val req = CompleteSessionRequestDto(force = true)
                    sessionApi.completeSession(sessionId, req)
                }
                _uiState.value = InterviewSessionScreenUiState.Completed
            } catch (e: Exception) {
                _uiState.value = InterviewSessionScreenUiState.Error("Failed to complete session: ${e.message}")
            }
        }
    }

    private fun startTimer() {
        viewModelScope.launch {
            while (true) {
                delay(1000)
                val currentState = _uiState.value
                if (currentState is InterviewSessionScreenUiState.Success) {
                    var nextState = currentState
                    if (nextState.phase == SessionPhase.RECORDING) {
                        nextState = nextState.copy(recordingTimeSeconds = nextState.recordingTimeSeconds + 1)
                    }
                    if (nextState.remainingTimeSeconds > 0 && nextState.phase != SessionPhase.REVIEW) {
                        nextState = nextState.copy(remainingTimeSeconds = nextState.remainingTimeSeconds - 1)
                    }
                    _uiState.value = nextState
                }
            }
        }
    }
}
