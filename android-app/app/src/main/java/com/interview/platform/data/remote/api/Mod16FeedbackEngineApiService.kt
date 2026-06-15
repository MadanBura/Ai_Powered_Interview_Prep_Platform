package com.interview.platform.data.remote.api

interface Mod16FeedbackEngineApiService {

    @retrofit2.http.GET("/api/v1/interviews/sessions/{sessionId}/feedback")
    suspend fun retrieveFeedback(@retrofit2.http.Path("sessionId") sessionId: String): retrofit2.Response<com.interview.platform.data.remote.dto.mod16_feedback_engine.FeedbackEngineResponseDto>

}
