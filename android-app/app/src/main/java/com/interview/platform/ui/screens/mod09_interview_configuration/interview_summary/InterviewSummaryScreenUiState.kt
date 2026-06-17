package com.interview.platform.ui.screens.mod09_interview_configuration.interview_summary

sealed class InterviewSummaryScreenUiState {
    object Loading : InterviewSummaryScreenUiState()
    object Empty : InterviewSummaryScreenUiState()
    data class Success(
        val data: String = "", 
        val isLoading: Boolean = false,
        val technology: String = "Technology Not Selected",
        val experienceLevel: String = "Level Not Selected",
        val questionCount: Int = 10
    ) : InterviewSummaryScreenUiState()
    data class Error(val message: String) : InterviewSummaryScreenUiState()
}
