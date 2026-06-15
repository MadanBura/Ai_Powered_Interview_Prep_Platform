package com.interview.platform.data.local.entity.mod04_technology_management

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mod04_technology_management_table")
data class TechnologyEntity(
    @PrimaryKey
    val id: String
)
