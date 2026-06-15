package com.interview.platform.ui.screens.mod09_interview_configuration.home

sealed class HomeDashboardScreenUiState {
    object Loading : HomeDashboardScreenUiState()
    object Empty : HomeDashboardScreenUiState()
    data class Success(val data: String = "") : HomeDashboardScreenUiState()
    data class Error(val message: String) : HomeDashboardScreenUiState()
}
