package com.interview.platform.ui.screens.mod17_reporting.interview_history

import com.interview.platform.data.remote.dto.mod10_interview_session.InterviewHistoryDataDto

sealed class InterviewHistoryScreenUiState {
    object Loading : InterviewHistoryScreenUiState()
    object Empty : InterviewHistoryScreenUiState()
    data class Success(val data: List<InterviewHistoryDataDto>) : InterviewHistoryScreenUiState()
    data class Error(val message: String) : InterviewHistoryScreenUiState()
}
