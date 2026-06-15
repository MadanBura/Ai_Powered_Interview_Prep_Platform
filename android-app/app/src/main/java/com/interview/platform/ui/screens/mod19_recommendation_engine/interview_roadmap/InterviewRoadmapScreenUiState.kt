package com.interview.platform.ui.screens.mod19_recommendation_engine.interview_roadmap

sealed class InterviewRoadmapScreenUiState {
    object Loading : InterviewRoadmapScreenUiState()
    object Empty : InterviewRoadmapScreenUiState()
    data class Success(
        val data: com.interview.platform.data.remote.dto.mod19_recommendation_engine.RoadmapResponseDto? = null,
        val userRoadmap: com.interview.platform.domain.model.mod19_recommendation_engine.UserRoadmap? = null,
        val badges: List<com.interview.platform.domain.model.mod19_recommendation_engine.UserBadge> = emptyList()
    ) : InterviewRoadmapScreenUiState()
    data class Error(val message: String) : InterviewRoadmapScreenUiState()
}
