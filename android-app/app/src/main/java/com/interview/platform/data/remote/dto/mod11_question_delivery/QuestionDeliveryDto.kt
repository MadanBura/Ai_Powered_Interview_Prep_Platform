package com.interview.platform.data.remote.dto.mod11_question_delivery

data class QuestionDeliveryResponseDto(
    val success: Boolean,
    val data: List<QuestionItemDto>? = null,
    val error: Any? = null
)

data class QuestionItemDto(
    val id: String,
    val text: String,
    val type: String? = null,
    val expectedDurationMinutes: Int? = null,
    val tags: List<String>? = null
)
