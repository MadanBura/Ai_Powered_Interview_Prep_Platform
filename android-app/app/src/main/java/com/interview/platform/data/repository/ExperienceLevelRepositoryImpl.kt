package com.interview.platform.data.repository

import com.interview.platform.data.local.dao.ExperienceLevelDao
import com.interview.platform.data.remote.api.Mod05ExperienceLevelManagementApiService
import com.interview.platform.domain.repository.ExperienceLevelRepository
import com.interview.platform.domain.model.mod05_experience_level_management.ExperienceLevel
import com.interview.platform.data.mapper.toDomain
import com.interview.platform.data.mapper.toEntity
import com.interview.platform.data.model.Result
import com.interview.platform.data.model.asResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class ExperienceLevelRepositoryImpl @Inject constructor(
    private val apiService: Mod05ExperienceLevelManagementApiService,
    private val dao: ExperienceLevelDao
) : ExperienceLevelRepository {

    override fun fetchAll(): Flow<Result<List<ExperienceLevel>>> {
        return dao.getAll()
            .map { list -> list.map { it.toDomain() } }
            .asResult()
    }

    override suspend fun syncData(): Result<Unit> {
        return try {
            Timber.d("Syncing ExperienceLevel data")
            Result.Success(Unit)
        } catch (e: Exception) {
            Timber.e(e, "Error syncing ExperienceLevel")
            Result.Error(e)
        }
    }
}
