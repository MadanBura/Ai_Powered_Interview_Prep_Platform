package com.interview.platform.data.local.entity.mod13_speech_to_text

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mod13_speech_to_text_table")
data class SpeechToTextEntity(
    @PrimaryKey
    val id: String
)
