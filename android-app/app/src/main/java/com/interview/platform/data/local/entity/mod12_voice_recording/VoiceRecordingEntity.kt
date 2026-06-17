package com.interview.platform.data.local.entity.mod12_voice_recording

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mod12_voice_recording_table")
data class VoiceRecordingEntity(
    @PrimaryKey
    val id: String
)
