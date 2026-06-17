package com.interview.platform.data.remote.dto.mod02_user_profile

data class ProfileRequestDto(
    val name: String? = null,
    val departmentId: String? = null,
    val technologies: String? = null,
    val experienceLevel: String? = null
)

data class ProfileDataDto(
    val id: String,
    val name: String? = null,
    val email: String? = null,
    val role: String? = null,
    val status: String? = null,
    val department: String? = null,
    val departmentId: String? = null,
    val technologies: String? = null,
    val experienceLevel: String? = null,
    val initial: String? = null
)

data class ProfileResponseDto(
    val success: Boolean,
    val data: ProfileDataDto? = null,
    val error: Any? = null
)
