package com.interview.platform.data.remote.api

interface Mod09InterviewConfigurationApiService {

    @retrofit2.http.GET("interviews/configuration/options")
    suspend fun fetchOptions(): retrofit2.Response<com.interview.platform.data.remote.dto.mod09_interview_configuration.InterviewConfigResponseDto>

}
