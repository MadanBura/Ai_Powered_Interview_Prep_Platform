package com.interview.platform.data.repository

import com.interview.platform.data.local.dao.FeedbackEngineDao
import com.interview.platform.data.remote.api.Mod16FeedbackEngineApiService
import com.interview.platform.domain.repository.FeedbackEngineRepository
import com.interview.platform.domain.model.mod16_feedback_engine.FeedbackEngine
import com.interview.platform.data.mapper.toDomain
import com.interview.platform.data.mapper.toEntity
import com.interview.platform.data.model.Result
import com.interview.platform.data.model.asResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class FeedbackEngineRepositoryImpl @Inject constructor(
    private val apiService: Mod16FeedbackEngineApiService,
    private val dao: FeedbackEngineDao
) : FeedbackEngineRepository {

    override fun fetchAll(): Flow<Result<List<FeedbackEngine>>> {
        return dao.getAll()
            .map { list -> list.map { it.toDomain() } }
            .asResult()
    }

    override suspend fun syncData(): Result<Unit> {
        return try {
            Timber.d("Syncing FeedbackEngine data")
            Result.Success(Unit)
        } catch (e: Exception) {
            Timber.e(e, "Error syncing FeedbackEngine")
            Result.Error(e)
        }
    }
}
