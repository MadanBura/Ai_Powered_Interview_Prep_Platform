package com.interview.platform.ui.screens.mod16_feedback_engine.ai_feedback

import com.interview.platform.domain.model.mod14_ai_evaluation.EvaluationResult

sealed class AiFeedbackReportScreenUiState {
    object Loading : AiFeedbackReportScreenUiState()
    object Empty : AiFeedbackReportScreenUiState()
    data class Success(
        val result: EvaluationResult,
        val isLoading: Boolean = false
    ) : AiFeedbackReportScreenUiState()
    data class Error(val message: String) : AiFeedbackReportScreenUiState()
}
