package com.interview.platform.ui.screens.mod09_interview_configuration.question_count_selection

sealed class QuestionCountSelectionScreenUiState {
    object Loading : QuestionCountSelectionScreenUiState()
    object Empty : QuestionCountSelectionScreenUiState()
    data class Success(val data: String = "") : QuestionCountSelectionScreenUiState()
    data class Error(val message: String) : QuestionCountSelectionScreenUiState()
}
