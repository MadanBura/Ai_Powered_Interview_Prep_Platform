package com.interview.platform.data.remote.dto.mod13_speech_to_text

data class SpeechToTextRequestDto(
    val payload: String? = null
)

data class SpeechToTextResponseDto(
    val success: Boolean,
    val data: SpeechToTextDataDto? = null
)

data class SpeechToTextDataDto(
    val id: String
)
