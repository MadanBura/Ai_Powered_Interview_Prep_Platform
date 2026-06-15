package com.interview.platform.data.remote.dto.mod03_department_management

data class DepartmentItemDto(
    val id: String,
    val name: String? = null,
    val status: String? = null
)

data class DepartmentDataDto(
    val departments: List<DepartmentItemDto>? = null,
    // Also used for single department responses
    val department: DepartmentItemDto? = null
)

data class DepartmentResponseDto(
    val success: Boolean,
    val data: DepartmentDataDto? = null,
    val error: Any? = null
)
