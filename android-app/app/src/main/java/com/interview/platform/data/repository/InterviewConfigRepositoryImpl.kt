package com.interview.platform.data.repository

import com.interview.platform.data.local.dao.InterviewConfigDao
import com.interview.platform.data.remote.api.Mod09InterviewConfigurationApiService
import com.interview.platform.domain.repository.InterviewConfigRepository
import com.interview.platform.domain.model.mod09_interview_configuration.InterviewConfig
import com.interview.platform.data.mapper.toDomain
import com.interview.platform.data.mapper.toEntity
import com.interview.platform.data.model.Result
import com.interview.platform.data.model.asResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class InterviewConfigRepositoryImpl @Inject constructor(
    private val apiService: Mod09InterviewConfigurationApiService,
    private val dao: InterviewConfigDao
) : InterviewConfigRepository {

    override fun fetchAll(): Flow<Result<List<InterviewConfig>>> {
        return dao.getAll()
            .map { list -> list.map { it.toDomain() } }
            .asResult()
    }

    override suspend fun syncData(): Result<Unit> {
        return try {
            Timber.d("Syncing InterviewConfig data")
            Result.Success(Unit)
        } catch (e: Exception) {
            Timber.e(e, "Error syncing InterviewConfig")
            Result.Error(e)
        }
    }
}
