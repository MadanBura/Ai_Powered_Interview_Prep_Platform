package com.interview.platform.data.remote.api

interface Mod14AiEvaluationApiService {

    @retrofit2.http.GET("interviews/sessions/{sessionId}/evaluation")
    suspend fun retrieveEvaluation(@retrofit2.http.Path("sessionId") sessionId: String): retrofit2.Response<com.interview.platform.data.remote.dto.mod14_ai_evaluation.AiEvaluationResponseDto>

}
