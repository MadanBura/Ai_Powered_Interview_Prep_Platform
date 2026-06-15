package com.interview.platform.ui.screens.mod10_interview_session.completed

sealed class InterviewCompletedScreenUiState {
    object Loading : InterviewCompletedScreenUiState()
    object Empty : InterviewCompletedScreenUiState()
    data class Success(
        val questionCount: Int = 15,
        val durationMinutes: Int = 45,
        val averageWpm: Int = 120
    ) : InterviewCompletedScreenUiState()
    data class Error(val message: String) : InterviewCompletedScreenUiState()
}
