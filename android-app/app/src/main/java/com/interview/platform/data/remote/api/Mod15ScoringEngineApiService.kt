package com.interview.platform.data.remote.api

interface Mod15ScoringEngineApiService {

    @retrofit2.http.GET("/api/v1/interviews/sessions/{sessionId}/scores")
    suspend fun retrieveScores(@retrofit2.http.Path("sessionId") sessionId: String): retrofit2.Response<com.interview.platform.data.remote.dto.mod15_scoring_engine.ScoringEngineResponseDto>

}
