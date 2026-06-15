package com.interview.platform.data.mapper

import com.interview.platform.data.local.entity.mod14_ai_evaluation.AiEvaluationEntity
import com.interview.platform.data.remote.dto.mod14_ai_evaluation.AiEvaluationDataDto
import com.interview.platform.domain.model.mod14_ai_evaluation.AiEvaluation

fun AiEvaluationDataDto.toEntity(): AiEvaluationEntity {
    return AiEvaluationEntity(id = this.id ?: java.util.UUID.randomUUID().toString())
}

fun AiEvaluationEntity.toDomain(): AiEvaluation {
    return AiEvaluation(id = this.id)
}
