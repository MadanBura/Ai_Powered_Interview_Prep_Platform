package com.interview.platform.data.remote.dto.mod18_dashboard

data class DashboardRequestDto(
    val payload: String? = null
)

data class DashboardResponseDto(
    val success: Boolean,
    val data: DashboardDataDto? = null
)

data class DashboardDataDto(
    val id: String
)
