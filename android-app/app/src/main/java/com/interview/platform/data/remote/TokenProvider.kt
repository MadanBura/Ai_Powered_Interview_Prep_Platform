package com.interview.platform.data.remote

import javax.inject.Inject
import javax.inject.Singleton

/**
 * In-memory token holder used by AuthInterceptor (OkHttp thread) to avoid
 * blocking coroutine/DataStore calls inside the interceptor.
 * Token is populated after successful OTP verification.
 */
@Singleton
class TokenProvider @Inject constructor() {
    var accessToken: String? = null
    var refreshToken: String? = null

    fun clear() {
        accessToken = null
        refreshToken = null
    }
}
