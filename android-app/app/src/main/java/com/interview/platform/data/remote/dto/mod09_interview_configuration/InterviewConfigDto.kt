package com.interview.platform.data.remote.dto.mod09_interview_configuration

import com.interview.platform.data.remote.dto.mod03_department_management.DepartmentItemDto
import com.interview.platform.data.remote.dto.mod04_technology_management.TechnologyItemDto
import com.interview.platform.data.remote.dto.mod05_experience_level_management.ExperienceLevelItemDto

data class InterviewConfigOptionsDataDto(
    val departments: List<DepartmentItemDto>? = null,
    val technologies: List<TechnologyItemDto>? = null,
    val experienceLevels: List<ExperienceLevelItemDto>? = null,
    val questionCounts: List<Int>? = null
)

data class InterviewConfigResponseDto(
    val success: Boolean,
    val data: InterviewConfigOptionsDataDto? = null,
    val error: Any? = null
)

// Request DTO for creating/updating config
data class InterviewConfigRequestDto(
    val departmentId: String? = null,
    val technologyIds: List<String>? = null,
    val experienceLevelId: String? = null,
    val questionCount: Int? = null
)
