package com.interview.platform.data.local.entity.mod20_analytics

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mod20_analytics_table")
data class AnalyticsEntity(
    @PrimaryKey
    val id: String
)
