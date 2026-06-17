package com.interview.platform.data.remote.api

interface Mod20AnalyticsApiService {

    @retrofit2.http.GET("/api/v1/admin/analytics/platform")
    suspend fun getPlatformMetrics(): retrofit2.Response<com.interview.platform.data.remote.dto.mod20_analytics.AnalyticsResponseDto>

}
