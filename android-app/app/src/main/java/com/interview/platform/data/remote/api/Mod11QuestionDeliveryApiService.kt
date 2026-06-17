package com.interview.platform.data.remote.api

interface Mod11QuestionDeliveryApiService {

    @retrofit2.http.GET("interviews/sessions/{sessionId}/questions")
    suspend fun retrieveQuestions(@retrofit2.http.Path("sessionId") sessionId: String): retrofit2.Response<com.interview.platform.data.remote.dto.mod11_question_delivery.QuestionDeliveryResponseDto>

}
