package com.interview.platform.data.mapper

import com.interview.platform.data.local.entity.mod01_authentication.AuthTokenEntity
import com.interview.platform.data.remote.dto.mod01_authentication.AuthResponseDto
import com.interview.platform.domain.model.mod01_authentication.AuthData

fun AuthResponseDto.toEntity(): AuthTokenEntity {
    return AuthTokenEntity(id = this.data?.accessToken ?: "")
}

fun AuthTokenEntity.toDomain(): AuthData {
    return AuthData(id = this.id)
}
