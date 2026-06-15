package com.interview.platform.data.local.entity.mod14_ai_evaluation

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mod14_ai_evaluation_table")
data class AiEvaluationEntity(
    @PrimaryKey
    val id: String
)
