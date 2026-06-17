package com.interview.platform.data.mapper

import com.interview.platform.data.local.entity.mod17_reporting.ReportingEntity
import com.interview.platform.data.remote.dto.mod17_reporting.ReportingDataDto
import com.interview.platform.domain.model.mod17_reporting.Reporting

fun ReportingDataDto.toEntity(): ReportingEntity {
    return ReportingEntity(id = this.id)
}

fun ReportingEntity.toDomain(): Reporting {
    return Reporting(id = this.id)
}
