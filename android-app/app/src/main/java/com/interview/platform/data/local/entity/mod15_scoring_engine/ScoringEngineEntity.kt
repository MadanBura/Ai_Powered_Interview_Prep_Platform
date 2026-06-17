package com.interview.platform.data.local.entity.mod15_scoring_engine

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mod15_scoring_engine_table")
data class ScoringEngineEntity(
    @PrimaryKey
    val id: String
)
