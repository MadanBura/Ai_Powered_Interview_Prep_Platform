package com.interview.platform.data.mapper

import com.interview.platform.data.local.entity.mod04_technology_management.TechnologyEntity
import com.interview.platform.data.remote.dto.mod04_technology_management.TechnologyItemDto
import com.interview.platform.domain.model.mod04_technology_management.Technology

fun TechnologyItemDto.toEntity(): TechnologyEntity {
    return TechnologyEntity(id = this.id)
}

fun TechnologyEntity.toDomain(): Technology {
    return Technology(id = this.id)
}
