package com.interview.platform.ui.screens.mod11_question_delivery.question_submitted

sealed class QuestionSubmittedScreenUiState {
    object Loading : QuestionSubmittedScreenUiState()
    object Empty : QuestionSubmittedScreenUiState()
    data class Success(
        val durationStr: String = "02:45",
        val wordCount: Int = 312,
        val scorePercent: Int = 80
    ) : QuestionSubmittedScreenUiState()
    data class Error(val message: String) : QuestionSubmittedScreenUiState()
}
