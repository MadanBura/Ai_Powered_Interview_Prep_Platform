package com.interview.platform.data.remote.dto.mod16_feedback_engine

data class FeedbackEngineRequestDto(
    val payload: String? = null
)

data class FeedbackEngineResponseDto(
    val success: Boolean,
    val data: FeedbackEngineDataDto? = null
)

data class FeedbackEngineDataDto(
    val id: String
)
