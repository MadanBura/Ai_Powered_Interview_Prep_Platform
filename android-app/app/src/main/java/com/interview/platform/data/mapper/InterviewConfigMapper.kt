package com.interview.platform.data.mapper

import com.interview.platform.data.local.entity.mod09_interview_configuration.InterviewConfigEntity
import com.interview.platform.data.remote.dto.mod09_interview_configuration.InterviewConfigOptionsDataDto
import com.interview.platform.domain.model.mod09_interview_configuration.InterviewConfig

fun InterviewConfigOptionsDataDto.toEntity(): InterviewConfigEntity {
    // Store as a stub entity — detailed data handled directly from DTO in ViewModels
    return InterviewConfigEntity(id = "options")
}

fun InterviewConfigEntity.toDomain(): InterviewConfig {
    return InterviewConfig(id = this.id)
}
