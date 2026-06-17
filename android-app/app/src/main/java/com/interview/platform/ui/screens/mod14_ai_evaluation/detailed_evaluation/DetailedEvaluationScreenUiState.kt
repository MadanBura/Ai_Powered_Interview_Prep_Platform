package com.interview.platform.ui.screens.mod14_ai_evaluation.detailed_evaluation

import com.interview.platform.domain.model.mod14_ai_evaluation.EvaluationResult

sealed class DetailedEvaluationScreenUiState {
    object Loading : DetailedEvaluationScreenUiState()
    object Empty : DetailedEvaluationScreenUiState()
    data class Success(
        val result: EvaluationResult,
        val isLoading: Boolean = false
    ) : DetailedEvaluationScreenUiState()
    data class Error(val message: String) : DetailedEvaluationScreenUiState()
}
