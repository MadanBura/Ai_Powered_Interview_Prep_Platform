package com.interview.platform.ui.screens.mod14_ai_evaluation.detailed_evaluation

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
class DetailedEvaluationScreenViewModel @Inject constructor(
    private val sessionManager: InterviewSessionManager
) : ViewModel() {
    private val _uiState = MutableStateFlow<DetailedEvaluationScreenUiState>(DetailedEvaluationScreenUiState.Loading)
    val uiState: StateFlow<DetailedEvaluationScreenUiState> = _uiState.asStateFlow()
    
    init {
        loadEvaluation()
    }

    private fun loadEvaluation() {
        val cached = sessionManager.evaluationResult
        if (cached != null) {
            _uiState.value = DetailedEvaluationScreenUiState.Success(result = cached)
        } else {
            _uiState.value = DetailedEvaluationScreenUiState.Error("No evaluation result available")
        }
    }
}
