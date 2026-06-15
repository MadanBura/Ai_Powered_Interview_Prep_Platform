package com.interview.platform.data.local.entity.mod16_feedback_engine

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mod16_feedback_engine_table")
data class FeedbackEngineEntity(
    @PrimaryKey
    val id: String
)
