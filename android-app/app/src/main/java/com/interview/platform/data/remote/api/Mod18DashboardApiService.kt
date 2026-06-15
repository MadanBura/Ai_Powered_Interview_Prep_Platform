package com.interview.platform.data.remote.api

interface Mod18DashboardApiService {

    @retrofit2.http.GET("/api/v1/candidates/dashboard")
    suspend fun retrieveCandidateDashboard(): retrofit2.Response<com.interview.platform.data.remote.dto.mod18_dashboard.DashboardResponseDto>

}
