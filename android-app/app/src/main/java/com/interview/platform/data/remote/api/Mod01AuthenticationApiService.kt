package com.interview.platform.data.remote.api

import com.interview.platform.data.remote.dto.mod01_authentication.AuthResponseDto
import com.interview.platform.data.remote.dto.mod01_authentication.OtpRequestDto
import com.interview.platform.data.remote.dto.mod01_authentication.OtpVerifyDto

interface Mod01AuthenticationApiService {

    @retrofit2.http.POST("auth/otp/request")
    suspend fun requestOtp(@retrofit2.http.Body request: OtpRequestDto): retrofit2.Response<AuthResponseDto>

    @retrofit2.http.POST("auth/otp/verify")
    suspend fun verifyOtp(@retrofit2.http.Body request: OtpVerifyDto): retrofit2.Response<AuthResponseDto>

}
