package com.interview.platform.data.repository

import com.interview.platform.data.local.dao.ScoringEngineDao
import com.interview.platform.data.remote.api.Mod15ScoringEngineApiService
import com.interview.platform.domain.repository.ScoringEngineRepository
import com.interview.platform.domain.model.mod15_scoring_engine.ScoringEngine
import com.interview.platform.data.mapper.toDomain
import com.interview.platform.data.mapper.toEntity
import com.interview.platform.data.model.Result
import com.interview.platform.data.model.asResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class ScoringEngineRepositoryImpl @Inject constructor(
    private val apiService: Mod15ScoringEngineApiService,
    private val dao: ScoringEngineDao
) : ScoringEngineRepository {

    override fun fetchAll(): Flow<Result<List<ScoringEngine>>> {
        return dao.getAll()
            .map { list -> list.map { it.toDomain() } }
            .asResult()
    }

    override suspend fun syncData(): Result<Unit> {
        return try {
            Timber.d("Syncing ScoringEngine data")
            Result.Success(Unit)
        } catch (e: Exception) {
            Timber.e(e, "Error syncing ScoringEngine")
            Result.Error(e)
        }
    }
}
