package com.interview.platform.data.local.entity.mod02_user_profile

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mod02_user_profile_table")
data class CandidateProfileEntity(
    @PrimaryKey
    val id: String
)
