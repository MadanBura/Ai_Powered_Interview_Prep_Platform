package com.interview.platform.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import java.util.UUID
import javax.inject.Inject

class CorrelationIdInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("X-Correlation-Id", UUID.randomUUID().toString())
            .build()
        return chain.proceed(request)
    }
}
