package com.interview.platform.data.remote.dto.mod04_technology_management

data class TechnologyItemDto(
    val id: String,
    val name: String? = null,
    val status: String? = null
)

data class TechnologyDataDto(
    val technologies: List<TechnologyItemDto>? = null,
    val technology: TechnologyItemDto? = null
)

data class TechnologyResponseDto(
    val success: Boolean,
    val data: TechnologyDataDto? = null,
    val error: Any? = null
)
