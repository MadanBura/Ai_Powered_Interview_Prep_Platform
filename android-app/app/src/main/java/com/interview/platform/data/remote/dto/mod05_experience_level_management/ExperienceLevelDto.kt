package com.interview.platform.data.remote.dto.mod05_experience_level_management

data class ExperienceLevelItemDto(
    val id: String,
    val name: String? = null,
    val description: String? = null,
    val minYears: Int? = null,
    val maxYears: Int? = null
)

data class ExperienceLevelDataDto(
    val experienceLevels: List<ExperienceLevelItemDto>? = null,
    val experienceLevel: ExperienceLevelItemDto? = null
)

data class ExperienceLevelResponseDto(
    val success: Boolean,
    val data: ExperienceLevelDataDto? = null,
    val error: Any? = null
)
