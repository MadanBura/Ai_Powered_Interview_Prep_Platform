package com.interview.platform.data.remote.dto.mod17_reporting

data class ReportingRequestDto(
    val payload: String? = null
)

data class ReportingResponseDto(
    val success: Boolean,
    val data: ReportingDataDto? = null
)

data class ReportingDataDto(
    val id: String
)
