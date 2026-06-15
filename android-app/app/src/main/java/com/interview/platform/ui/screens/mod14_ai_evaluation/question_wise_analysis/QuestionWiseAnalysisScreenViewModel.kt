package com.interview.platform.ui.screens.mod14_ai_evaluation.question_wise_analysis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.platform.data.repository.InterviewSessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionWiseAnalysisScreenViewModel @Inject constructor(
    private val sessionManager: InterviewSessionManager
) : ViewModel() {
    private val _uiState = MutableStateFlow<QuestionWiseAnalysisScreenUiState>(QuestionWiseAnalysisScreenUiState.Loading)
    val uiState: StateFlow<QuestionWiseAnalysisScreenUiState> = _uiState.asStateFlow()
    
    init {
        loadAnalysis()
    }

    private fun loadAnalysis() {
        val cached = sessionManager.evaluationResult
        if (cached != null) {
            _uiState.value = QuestionWiseAnalysisScreenUiState.Success(result = cached)
        } else {
            _uiState.value = QuestionWiseAnalysisScreenUiState.Error("No evaluation result available")
        }
    }
}
