package com.interview.platform.ui.screens.mod10_interview_session.audio_review

sealed class AudioReviewScreenUiState {
    object Loading : AudioReviewScreenUiState()
    object Empty : AudioReviewScreenUiState()
    data class Success(
        val isPlaying: Boolean = false,
        val progress: Float = 0f,
        val currentTimeStr: String = "0:00",
        val totalTimeStr: String = "1:20",
        val isLoading: Boolean = false
    ) : AudioReviewScreenUiState()
    data class Error(val message: String) : AudioReviewScreenUiState()
}
