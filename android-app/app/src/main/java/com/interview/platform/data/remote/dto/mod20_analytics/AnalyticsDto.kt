package com.interview.platform.data.remote.dto.mod20_analytics

data class AnalyticsRequestDto(
    val payload: String? = null
)

data class AnalyticsResponseDto(
    val success: Boolean,
    val data: AnalyticsDataDto? = null
)

data class AnalyticsDataDto(
    val id: String
)
