package com.interview.platform.data.remote.api

interface Mod02UserProfileApiService {

    @retrofit2.http.GET("users/me")
    suspend fun getProfile(): retrofit2.Response<com.interview.platform.data.remote.dto.mod02_user_profile.ProfileResponseDto>

    @retrofit2.http.PUT("users/me")
    suspend fun updateProfile(
        @retrofit2.http.Body request: com.interview.platform.data.remote.dto.mod02_user_profile.ProfileRequestDto
    ): retrofit2.Response<com.interview.platform.data.remote.dto.mod02_user_profile.ProfileResponseDto>

}
