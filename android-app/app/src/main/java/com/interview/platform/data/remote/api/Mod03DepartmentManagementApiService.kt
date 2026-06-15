package com.interview.platform.data.remote.api

interface Mod03DepartmentManagementApiService {

    @retrofit2.http.GET("admin/departments")
    suspend fun listDepartments(): retrofit2.Response<com.interview.platform.data.remote.dto.mod03_department_management.DepartmentResponseDto>

}
