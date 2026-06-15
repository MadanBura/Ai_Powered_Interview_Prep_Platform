package com.interview.platform.ui.screens.mod14_ai_evaluation.question_wise_analysis

import com.interview.platform.domain.model.mod14_ai_evaluation.EvaluationResult

sealed class QuestionWiseAnalysisScreenUiState {
    object Loading : QuestionWiseAnalysisScreenUiState()
    object Empty : QuestionWiseAnalysisScreenUiState()
    data class Success(
        val result: EvaluationResult,
        val isLoading: Boolean = false
    ) : QuestionWiseAnalysisScreenUiState()
    data class Error(val message: String) : QuestionWiseAnalysisScreenUiState()
}
