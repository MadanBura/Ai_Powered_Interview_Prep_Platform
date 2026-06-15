package com.interview.platform.data.repository

import com.interview.platform.data.local.dao.VoiceRecordingDao
import com.interview.platform.data.remote.api.Mod12VoiceRecordingApiService
import com.interview.platform.domain.repository.VoiceRecordingRepository
import com.interview.platform.domain.model.mod12_voice_recording.VoiceRecording
import com.interview.platform.data.mapper.toDomain
import com.interview.platform.data.mapper.toEntity
import com.interview.platform.data.model.Result
import com.interview.platform.data.model.asResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class VoiceRecordingRepositoryImpl @Inject constructor(
    private val apiService: Mod12VoiceRecordingApiService,
    private val dao: VoiceRecordingDao
) : VoiceRecordingRepository {

    override fun fetchAll(): Flow<Result<List<VoiceRecording>>> {
        return dao.getAll()
            .map { list -> list.map { it.toDomain() } }
            .asResult()
    }

    override suspend fun syncData(): Result<Unit> {
        return try {
            Timber.d("Syncing VoiceRecording data")
            Result.Success(Unit)
        } catch (e: Exception) {
            Timber.e(e, "Error syncing VoiceRecording")
            Result.Error(e)
        }
    }
}
