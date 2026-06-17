package com.interview.platform.data.mapper

import com.interview.platform.data.local.entity.mod18_dashboard.DashboardEntity
import com.interview.platform.data.remote.dto.mod18_dashboard.DashboardDataDto
import com.interview.platform.domain.model.mod18_dashboard.Dashboard

fun DashboardDataDto.toEntity(): DashboardEntity {
    return DashboardEntity(id = this.id)
}

fun DashboardEntity.toDomain(): Dashboard {
    return Dashboard(id = this.id)
}
