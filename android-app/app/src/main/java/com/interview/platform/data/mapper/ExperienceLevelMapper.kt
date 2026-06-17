package com.interview.platform.data.mapper

import com.interview.platform.data.local.entity.mod05_experience_level_management.ExperienceLevelEntity
import com.interview.platform.data.remote.dto.mod05_experience_level_management.ExperienceLevelItemDto
import com.interview.platform.domain.model.mod05_experience_level_management.ExperienceLevel

fun ExperienceLevelItemDto.toEntity(): ExperienceLevelEntity {
    return ExperienceLevelEntity(id = this.id)
}

fun ExperienceLevelEntity.toDomain(): ExperienceLevel {
    return ExperienceLevel(id = this.id)
}
