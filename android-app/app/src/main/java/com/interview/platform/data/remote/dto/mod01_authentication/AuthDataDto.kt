package com.interview.platform.data.remote.dto.mod01_authentication

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OtpRequestDto(
    @Json(name = "target") val target: String
)

@JsonClass(generateAdapter = true)
data class OtpVerifyDto(
    @Json(name = "target") val target: String,
    @Json(name = "otpCode") val otpCode: String
)

@JsonClass(generateAdapter = true)
data class AuthTokensDto(
    @Json(name = "accessToken") val accessTokenCamel: String? = null,
    @Json(name = "refreshToken") val refreshTokenCamel: String? = null,
    @Json(name = "expiresIn") val expiresIn: Long? = null,
    @Json(name = "tokenType") val tokenType: String? = null,
    @Json(name = "role") val role: String? = null,
    @Json(name = "access_token") val accessTokenSnake: String? = null,
    @Json(name = "refresh_token") val refreshTokenSnake: String? = null
) {
    val accessToken: String
        get() = accessTokenCamel ?: accessTokenSnake ?: ""

    val refreshToken: String
        get() = refreshTokenCamel ?: refreshTokenSnake ?: ""
}

@JsonClass(generateAdapter = true)
data class AuthResponseDto(
    @Json(name = "success") val success: Boolean,
    @Json(name = "data") val data: AuthTokensDto? = null,
    @Json(name = "error") val error: Any? = null
)
