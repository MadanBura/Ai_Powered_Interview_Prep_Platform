package com.interview.platform.data.remote.api

interface Mod17ReportingApiService {

    @retrofit2.http.GET("/api/v1/candidates/reports")
    suspend fun listPastReports(): retrofit2.Response<com.interview.platform.data.remote.dto.mod17_reporting.ReportingResponseDto>

}
