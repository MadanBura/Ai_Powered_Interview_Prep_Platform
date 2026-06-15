package com.interview.platform.data.remote.api

import com.interview.platform.data.remote.dto.mod10_interview_session.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface Mod10InterviewSessionApiService {

    @POST("interviews/sessions")
    suspend fun initializeSession(
        @Body request: InterviewSessionRequestDto
    ): Response<InterviewSessionResponseDto>

    @POST("interviews/sessions/{sessionId}/answers")
    suspend fun submitAnswer(
        @Path("sessionId") sessionId: String,
        @Body request: SubmitAnswerRequestDto
    ): Response<SubmitAnswerResponseDto>

    @POST("interviews/sessions/{sessionId}/complete")
    suspend fun completeSession(
        @Path("sessionId") sessionId: String,
        @Body request: CompleteSessionRequestDto
    ): Response<CompleteSessionResponseDto>

    @retrofit2.http.GET("interviews/sessions/history")
    suspend fun getInterviewHistory(): Response<InterviewHistoryResponseDto>
}
