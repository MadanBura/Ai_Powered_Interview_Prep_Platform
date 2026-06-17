package com.interview.platform.data.mapper

import com.interview.platform.data.local.entity.mod12_voice_recording.VoiceRecordingEntity
import com.interview.platform.data.remote.dto.mod12_voice_recording.VoiceRecordingDataDto
import com.interview.platform.domain.model.mod12_voice_recording.VoiceRecording

fun VoiceRecordingDataDto.toEntity(): VoiceRecordingEntity {
    return VoiceRecordingEntity(id = this.id)
}

fun VoiceRecordingEntity.toDomain(): VoiceRecording {
    return VoiceRecording(id = this.id)
}
