package com.interview.platform.data.remote.dto.mod12_voice_recording

data class VoiceRecordingRequestDto(
    val payload: String? = null
)

data class VoiceRecordingResponseDto(
    val success: Boolean,
    val data: VoiceRecordingDataDto? = null
)

data class VoiceRecordingDataDto(
    val id: String
)
