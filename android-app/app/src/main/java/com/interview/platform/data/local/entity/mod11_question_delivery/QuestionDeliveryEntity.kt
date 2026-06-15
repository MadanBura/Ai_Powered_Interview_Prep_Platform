package com.interview.platform.data.local.entity.mod11_question_delivery

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mod11_question_delivery_table")
data class QuestionDeliveryEntity(
    @PrimaryKey
    val id: String
)
