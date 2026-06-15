package com.interview.platform.data.remote.api

interface Mod13SpeechToTextApiService {

    @retrofit2.http.POST("/api/v1/media/audio/transcribe")
    suspend fun submitAudioForStt(@retrofit2.http.Body request: com.interview.platform.data.remote.dto.mod13_speech_to_text.SpeechToTextRequestDto): retrofit2.Response<com.interview.platform.data.remote.dto.mod13_speech_to_text.SpeechToTextResponseDto>

}
