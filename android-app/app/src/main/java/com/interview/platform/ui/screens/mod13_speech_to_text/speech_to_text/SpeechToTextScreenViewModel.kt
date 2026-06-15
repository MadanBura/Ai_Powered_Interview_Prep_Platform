package com.interview.platform.ui.screens.mod13_speech_to_text.speech_to_text

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.platform.data.remote.api.Mod10InterviewSessionApiService
import com.interview.platform.data.remote.dto.mod10_interview_session.SubmitAnswerRequestDto
import com.interview.platform.data.repository.InterviewSessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.interview.platform.domain.audio.SpeechRecognizerService

@HiltViewModel
class SpeechToTextScreenViewModel @Inject constructor(
    private val speechRecognizer: SpeechRecognizerService,
    private val sessionApi: Mod10InterviewSessionApiService,
    private val sessionManager: InterviewSessionManager
) : ViewModel() {
    private val _uiState = MutableStateFlow<SpeechToTextScreenUiState>(SpeechToTextScreenUiState.Loading)
    val uiState: StateFlow<SpeechToTextScreenUiState> = _uiState.asStateFlow()
    
    init {
        val currentQ = sessionManager.currentQuestion
        if (currentQ != null) {
            _uiState.value = SpeechToTextScreenUiState.Success(
                questionText = currentQ.text,
                currentStep = sessionManager.currentQuestionIndex + 1,
                totalSteps = sessionManager.sessionQuestions.size
            )
        } else {
            _uiState.value = SpeechToTextScreenUiState.Error("No active question found")
        }
    }
    
    fun toggleEdit() {
        val currentState = _uiState.value as? SpeechToTextScreenUiState.Success ?: return
        _uiState.value = currentState.copy(isEditing = !currentState.isEditing)
    }

    fun updateText(newText: String) {
        val currentState = _uiState.value as? SpeechToTextScreenUiState.Success ?: return
        _uiState.value = currentState.copy(transcribedText = newText)
    }

    fun startListening() {
        val currentState = _uiState.value as? SpeechToTextScreenUiState.Success ?: return
        if (currentState.isRecording) return

        _uiState.value = currentState.copy(isRecording = true, transcribedText = "")
        
        speechRecognizer.startListening(
            onResult = { finalResult ->
                val state = _uiState.value as? SpeechToTextScreenUiState.Success ?: return@startListening
                _uiState.value = state.copy(transcribedText = finalResult, isRecording = false)
            },
            onPartialResult = { partialResult ->
                val state = _uiState.value as? SpeechToTextScreenUiState.Success ?: return@startListening
                _uiState.value = state.copy(transcribedText = partialResult)
            },
            onError = { error ->
                val state = _uiState.value as? SpeechToTextScreenUiState.Success ?: return@startListening
                // In a real app we might show a toast, but here we just stop recording
                _uiState.value = state.copy(isRecording = false)
            }
        )
    }

    fun stopListening() {
        speechRecognizer.stopListening()
        val currentState = _uiState.value as? SpeechToTextScreenUiState.Success ?: return
        _uiState.value = currentState.copy(isRecording = false)
    }

    override fun onCleared() {
        super.onCleared()
        speechRecognizer.stopListening()
    }

    fun submitAction(onSuccess: () -> Unit) {
        val currentState = _uiState.value as? SpeechToTextScreenUiState.Success ?: return
        val sessionId = sessionManager.currentSessionId
        val currentQ = sessionManager.currentQuestion
        
        if (sessionId == null || currentQ == null) {
            return
        }

        _uiState.value = currentState.copy(isLoading = true)
        
        viewModelScope.launch {
            try {
                val req = SubmitAnswerRequestDto(
                    questionId = currentQ.id,
                    answerText = currentState.transcribedText
                )
                val response = sessionApi.submitAnswer(sessionId, req)
                if (response.isSuccessful) {
                    val nextIdx = sessionManager.currentQuestionIndex + 1
                    sessionManager.currentQuestionIndex = nextIdx
                    onSuccess()
                } else {
                    _uiState.value = SpeechToTextScreenUiState.Error("Failed to submit answer")
                }
            } catch (e: Exception) {
                _uiState.value = SpeechToTextScreenUiState.Error("Network Error: ${e.message}")
            }
        }
    }
}
