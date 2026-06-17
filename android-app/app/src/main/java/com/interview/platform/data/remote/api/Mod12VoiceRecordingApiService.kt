package com.interview.platform.data.remote.api

interface Mod12VoiceRecordingApiService {

    @retrofit2.http.POST("/api/v1/media/audio/upload-url")
    suspend fun requestUploadUrl(@retrofit2.http.Body request: com.interview.platform.data.remote.dto.mod12_voice_recording.VoiceRecordingRequestDto): retrofit2.Response<com.interview.platform.data.remote.dto.mod12_voice_recording.VoiceRecordingResponseDto>

}
