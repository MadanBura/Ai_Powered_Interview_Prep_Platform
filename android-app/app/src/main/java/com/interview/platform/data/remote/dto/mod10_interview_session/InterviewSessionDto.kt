package com.interview.platform.data.remote.dto.mod10_interview_session

data class InterviewSessionRequestDto(
    val departmentId: String? = null,
    val technologyIds: List<String>? = null,
    val experienceLevelId: String? = null,
    val technologyName: String? = null
)

data class InterviewSessionDataDto(
    val id: String,
    val status: String? = null,
    val startedAt: String? = null,
    val endedAt: String? = null
)

data class InterviewSessionResponseDto(
    val success: Boolean,
    val data: InterviewSessionDataDto? = null,
    val error: Any? = null
)

// Answer DTOs
data class SubmitAnswerRequestDto(
    val questionId: String,
    val answerText: String? = null,
    val audioUrl: String? = null
)

data class SubmitAnswerResponseDto(
    val success: Boolean,
    val data: Any? = null,
    val error: Any? = null
)

// Complete DTOs
data class CompleteSessionRequestDto(
    val force: Boolean = false
)

data class CompleteSessionResponseDto(
    val success: Boolean,
    val data: Any? = null,
    val error: Any? = null
)
