package com.interview.platform.data.mapper

import com.interview.platform.data.local.entity.mod16_feedback_engine.FeedbackEngineEntity
import com.interview.platform.data.remote.dto.mod16_feedback_engine.FeedbackEngineDataDto
import com.interview.platform.domain.model.mod16_feedback_engine.FeedbackEngine

fun FeedbackEngineDataDto.toEntity(): FeedbackEngineEntity {
    return FeedbackEngineEntity(id = this.id)
}

fun FeedbackEngineEntity.toDomain(): FeedbackEngine {
    return FeedbackEngine(id = this.id)
}
