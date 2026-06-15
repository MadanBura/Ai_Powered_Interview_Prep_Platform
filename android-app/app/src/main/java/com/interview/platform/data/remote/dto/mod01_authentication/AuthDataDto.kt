package com.interview.platform.data.remote.dto.mod01_authentication

data class OtpRequestDto(
    val target: String
)

data class OtpVerifyDto(
    val target: String,
    val otpCode: String
)

data class AuthTokensDto(
    val accessToken: String,
    val refreshToken: String,
    val expiresIn: Long,
    val tokenType: String,
    val role: String
)

data class AuthResponseDto(
    val success: Boolean,
    val data: AuthTokensDto? = null,
    val error: Any? = null
)
