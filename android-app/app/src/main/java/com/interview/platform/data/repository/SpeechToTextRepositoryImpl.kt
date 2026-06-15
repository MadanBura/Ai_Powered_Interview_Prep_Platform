package com.interview.platform.data.repository

import com.interview.platform.data.local.dao.SpeechToTextDao
import com.interview.platform.data.remote.api.Mod13SpeechToTextApiService
import com.interview.platform.domain.repository.SpeechToTextRepository
import com.interview.platform.domain.model.mod13_speech_to_text.SpeechToText
import com.interview.platform.data.mapper.toDomain
import com.interview.platform.data.mapper.toEntity
import com.interview.platform.data.model.Result
import com.interview.platform.data.model.asResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class SpeechToTextRepositoryImpl @Inject constructor(
    private val apiService: Mod13SpeechToTextApiService,
    private val dao: SpeechToTextDao
) : SpeechToTextRepository {

    override fun fetchAll(): Flow<Result<List<SpeechToText>>> {
        return dao.getAll()
            .map { list -> list.map { it.toDomain() } }
            .asResult()
    }

    override suspend fun syncData(): Result<Unit> {
        return try {
            Timber.d("Syncing SpeechToText data")
            Result.Success(Unit)
        } catch (e: Exception) {
            Timber.e(e, "Error syncing SpeechToText")
            Result.Error(e)
        }
    }
}
