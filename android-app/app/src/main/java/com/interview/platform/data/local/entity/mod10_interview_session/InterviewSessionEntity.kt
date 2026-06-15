package com.interview.platform.data.local.entity.mod10_interview_session

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mod10_interview_session_table")
data class InterviewSessionEntity(
    @PrimaryKey
    val id: String
)
