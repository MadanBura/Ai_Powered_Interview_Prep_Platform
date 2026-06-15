package com.interview.platform.data.local.entity.mod05_experience_level_management

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mod05_experience_level_management_table")
data class ExperienceLevelEntity(
    @PrimaryKey
    val id: String
)
