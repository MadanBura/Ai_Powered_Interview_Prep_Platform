package com.interview.platform.data.local.entity.mod18_dashboard

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mod18_dashboard_table")
data class DashboardEntity(
    @PrimaryKey
    val id: String
)
