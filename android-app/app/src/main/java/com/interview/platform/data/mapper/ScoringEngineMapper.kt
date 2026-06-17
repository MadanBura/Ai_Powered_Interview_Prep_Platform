package com.interview.platform.data.mapper

import com.interview.platform.data.local.entity.mod15_scoring_engine.ScoringEngineEntity
import com.interview.platform.data.remote.dto.mod15_scoring_engine.ScoringEngineDataDto
import com.interview.platform.domain.model.mod15_scoring_engine.ScoringEngine

fun ScoringEngineDataDto.toEntity(): ScoringEngineEntity {
    return ScoringEngineEntity(id = this.id)
}

fun ScoringEngineEntity.toDomain(): ScoringEngine {
    return ScoringEngine(id = this.id)
}
