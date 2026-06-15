package com.interview.platform.data.mapper

import com.interview.platform.data.local.entity.mod03_department_management.DepartmentEntity
import com.interview.platform.data.remote.dto.mod03_department_management.DepartmentItemDto
import com.interview.platform.domain.model.mod03_department_management.Department

fun DepartmentItemDto.toEntity(): DepartmentEntity {
    return DepartmentEntity(id = this.id)
}

fun DepartmentEntity.toDomain(): Department {
    return Department(id = this.id)
}
