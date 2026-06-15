package com.interview.platform.ui.screens.mod18_dashboard.achievements

sealed class AchievementsScreenUiState {
    object Loading : AchievementsScreenUiState()
    object Empty : AchievementsScreenUiState()
    data class Success(val data: String = "") : AchievementsScreenUiState()
    data class Error(val message: String) : AchievementsScreenUiState()
}
