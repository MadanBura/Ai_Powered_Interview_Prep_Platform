package com.interview.platform.data.mapper

import com.interview.platform.data.local.entity.mod13_speech_to_text.SpeechToTextEntity
import com.interview.platform.data.remote.dto.mod13_speech_to_text.SpeechToTextDataDto
import com.interview.platform.domain.model.mod13_speech_to_text.SpeechToText

fun SpeechToTextDataDto.toEntity(): SpeechToTextEntity {
    return SpeechToTextEntity(id = this.id)
}

fun SpeechToTextEntity.toDomain(): SpeechToText {
    return SpeechToText(id = this.id)
}
