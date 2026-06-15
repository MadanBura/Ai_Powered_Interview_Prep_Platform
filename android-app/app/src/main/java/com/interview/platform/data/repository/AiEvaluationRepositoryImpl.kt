package com.interview.platform.data.repository

import com.interview.platform.data.local.dao.AiEvaluationDao
import com.interview.platform.data.remote.api.Mod14AiEvaluationApiService
import com.interview.platform.domain.repository.AiEvaluationRepository
import com.interview.platform.domain.model.mod14_ai_evaluation.AiEvaluation
import com.interview.platform.data.mapper.toDomain
import com.interview.platform.data.mapper.toEntity
import com.interview.platform.data.model.Result
import com.interview.platform.data.model.asResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class AiEvaluationRepositoryImpl @Inject constructor(
    private val apiService: Mod14AiEvaluationApiService,
    private val dao: AiEvaluationDao
) : AiEvaluationRepository {

    override fun fetchAll(): Flow<Result<List<AiEvaluation>>> {
        return dao.getAll()
            .map { list -> list.map { it.toDomain() } }
            .asResult()
    }

    override suspend fun syncData(): Result<Unit> {
        return try {
            Timber.d("Syncing AiEvaluation data")
            Result.Success(Unit)
        } catch (e: Exception) {
            Timber.e(e, "Error syncing AiEvaluation")
            Result.Error(e)
        }
    }
}
