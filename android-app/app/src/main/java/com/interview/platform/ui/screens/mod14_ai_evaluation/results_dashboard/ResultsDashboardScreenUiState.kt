package com.interview.platform.ui.screens.mod14_ai_evaluation.results_dashboard

import com.interview.platform.domain.model.mod14_ai_evaluation.EvaluationResult

sealed class ResultsDashboardScreenUiState {
    object Loading : ResultsDashboardScreenUiState()
    object Empty : ResultsDashboardScreenUiState()
    data class Success(
        val result: EvaluationResult,
        val isLoading: Boolean = false
    ) : ResultsDashboardScreenUiState()
    data class Error(val message: String) : ResultsDashboardScreenUiState()
}
