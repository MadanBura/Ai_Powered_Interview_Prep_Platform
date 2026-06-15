package com.interview.platform.data.local.entity.mod17_reporting

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mod17_reporting_table")
data class ReportingEntity(
    @PrimaryKey
    val id: String
)
