package com.interview.platform.domain.repository

import com.interview.platform.domain.model.mod12_voice_recording.VoiceRecording
import com.interview.platform.data.model.Result
import kotlinx.coroutines.flow.Flow

interface VoiceRecordingRepository {
    fun fetchAll(): Flow<Result<List<VoiceRecording>>>
    suspend fun syncData(): Result<Unit>
}
