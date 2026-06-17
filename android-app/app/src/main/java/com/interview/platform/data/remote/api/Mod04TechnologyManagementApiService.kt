package com.interview.platform.data.remote.api

interface Mod04TechnologyManagementApiService {

    @retrofit2.http.GET("admin/technologies")
    suspend fun listTechnologies(): retrofit2.Response<com.interview.platform.data.remote.dto.mod04_technology_management.TechnologyResponseDto>

}
