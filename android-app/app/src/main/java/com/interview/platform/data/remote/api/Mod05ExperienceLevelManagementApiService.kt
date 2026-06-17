package com.interview.platform.data.remote.api

interface Mod05ExperienceLevelManagementApiService {

    @retrofit2.http.GET("admin/experience-levels")
    suspend fun listExperienceLevels(): retrofit2.Response<com.interview.platform.data.remote.dto.mod05_experience_level_management.ExperienceLevelResponseDto>

}
