package com.interview.platform.data.local.entity.mod09_interview_configuration

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mod09_interview_configuration_table")
data class InterviewConfigEntity(
    @PrimaryKey
    val id: String
)
