package com.interview.platform.ui.screens.mod05_experience_level_management.experience_selection

sealed class ExperienceSelectionScreenUiState {
    object Loading : ExperienceSelectionScreenUiState()
    object Empty : ExperienceSelectionScreenUiState()
    data class Success(val isLoading: Boolean = false, val defaultExperience: String = "") : ExperienceSelectionScreenUiState()
    data class Error(val message: String) : ExperienceSelectionScreenUiState()
}
