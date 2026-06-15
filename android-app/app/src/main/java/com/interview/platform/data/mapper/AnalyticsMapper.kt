package com.interview.platform.data.mapper

import com.interview.platform.data.local.entity.mod20_analytics.AnalyticsEntity
import com.interview.platform.data.remote.dto.mod20_analytics.AnalyticsDataDto
import com.interview.platform.domain.model.mod20_analytics.Analytics

fun AnalyticsDataDto.toEntity(): AnalyticsEntity {
    return AnalyticsEntity(id = this.id)
}

fun AnalyticsEntity.toDomain(): Analytics {
    return Analytics(id = this.id)
}
