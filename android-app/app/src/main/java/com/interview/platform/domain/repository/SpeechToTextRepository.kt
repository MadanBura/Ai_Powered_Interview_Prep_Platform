package com.interview.platform.domain.repository

import com.interview.platform.domain.model.mod13_speech_to_text.SpeechToText
import com.interview.platform.data.model.Result
import kotlinx.coroutines.flow.Flow

interface SpeechToTextRepository {
    fun fetchAll(): Flow<Result<List<SpeechToText>>>
    suspend fun syncData(): Result<Unit>
}
