package com.interview.platform.data.remote.dto.mod15_scoring_engine

data class ScoringEngineRequestDto(
    val payload: String? = null
)

data class ScoringEngineResponseDto(
    val success: Boolean,
    val data: ScoringEngineDataDto? = null
)

data class ScoringEngineDataDto(
    val id: String
)
