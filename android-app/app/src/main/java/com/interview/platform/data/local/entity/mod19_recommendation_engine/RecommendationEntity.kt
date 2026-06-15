package com.interview.platform.data.local.entity.mod19_recommendation_engine

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mod19_recommendation_engine_table")
data class RecommendationEntity(
    @PrimaryKey
    val id: String
)
