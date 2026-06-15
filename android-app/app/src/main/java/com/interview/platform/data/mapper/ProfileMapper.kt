package com.interview.platform.data.mapper

import com.interview.platform.data.local.entity.mod02_user_profile.CandidateProfileEntity
import com.interview.platform.data.remote.dto.mod02_user_profile.ProfileDataDto
import com.interview.platform.domain.model.mod02_user_profile.Profile

fun ProfileDataDto.toEntity(): CandidateProfileEntity {
    return CandidateProfileEntity(id = this.id)
}

fun CandidateProfileEntity.toDomain(): Profile {
    return Profile(id = this.id)
}
