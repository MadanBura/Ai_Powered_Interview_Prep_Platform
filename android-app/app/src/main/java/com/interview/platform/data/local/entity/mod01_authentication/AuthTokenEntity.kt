package com.interview.platform.data.local.entity.mod01_authentication

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mod01_authentication_table")
data class AuthTokenEntity(
    @PrimaryKey
    val id: String
)
