package com.interview.platform.ui.screens.mod19_recommendation_engine.recommended_technologies

sealed class RecommendedTechnologiesScreenUiState {
    object Loading : RecommendedTechnologiesScreenUiState()
    object Empty : RecommendedTechnologiesScreenUiState()
    data class Success(val roadmaps: List<com.interview.platform.data.remote.dto.mod19_recommendation_engine.AvailableRoadmapDto>) : RecommendedTechnologiesScreenUiState()
    data class Error(val message: String) : RecommendedTechnologiesScreenUiState()
}
