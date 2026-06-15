package com.interview.platform.data.remote.dto.mod10_interview_session

data class InterviewHistoryDataDto(
    val id: String,
    val role: String? = null,
    val startedAt: String? = null,
    val endedAt: String? = null,
    val duration: String? = null,
    val score: Int? = null,
    val status: String? = null
)

data class InterviewHistoryResponseDto(
    val success: Boolean,
    val data: List<InterviewHistoryDataDto>? = null,
    val error: Any? = null
)
