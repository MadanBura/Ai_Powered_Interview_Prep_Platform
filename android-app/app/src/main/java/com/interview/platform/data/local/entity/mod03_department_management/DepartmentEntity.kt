package com.interview.platform.data.local.entity.mod03_department_management

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mod03_department_management_table")
data class DepartmentEntity(
    @PrimaryKey
    val id: String
)
