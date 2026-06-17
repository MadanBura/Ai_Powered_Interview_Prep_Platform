package com.interview.platform.data.remote.dto.mod19_recommendation_engine

data class RecommendationRequestDto(
    val payload: String? = null
)

data class RecommendationResponseDto(
    val success: Boolean,
    val data: RecommendationDataDto? = null
)

data class RecommendationDataDto(
    val id: String
)
