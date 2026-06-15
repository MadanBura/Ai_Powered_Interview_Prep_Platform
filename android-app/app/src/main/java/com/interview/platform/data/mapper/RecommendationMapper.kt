package com.interview.platform.data.mapper

import com.interview.platform.data.local.entity.mod19_recommendation_engine.RecommendationEntity
import com.interview.platform.data.remote.dto.mod19_recommendation_engine.RecommendationDataDto
import com.interview.platform.domain.model.mod19_recommendation_engine.Recommendation

fun RecommendationDataDto.toEntity(): RecommendationEntity {
    return RecommendationEntity(id = this.id)
}

fun RecommendationEntity.toDomain(): Recommendation {
    return Recommendation(id = this.id)
}
