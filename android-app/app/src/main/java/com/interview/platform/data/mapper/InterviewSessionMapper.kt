package com.interview.platform.data.mapper

import com.interview.platform.data.local.entity.mod10_interview_session.InterviewSessionEntity
import com.interview.platform.data.remote.dto.mod10_interview_session.InterviewSessionDataDto
import com.interview.platform.domain.model.mod10_interview_session.InterviewSession

fun InterviewSessionDataDto.toEntity(): InterviewSessionEntity {
    return InterviewSessionEntity(id = this.id)
}

fun InterviewSessionEntity.toDomain(): InterviewSession {
    return InterviewSession(id = this.id)
}
