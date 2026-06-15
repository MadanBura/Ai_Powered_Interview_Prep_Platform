package com.interview.platform.ui.screens.mod19_recommendation_engine.learning_recommendations

sealed class LearningRecommendationsScreenUiState {
    object Loading : LearningRecommendationsScreenUiState()
    object Empty : LearningRecommendationsScreenUiState()
    data class Success(val data: String = "") : LearningRecommendationsScreenUiState()
    data class Error(val message: String) : LearningRecommendationsScreenUiState()
}
