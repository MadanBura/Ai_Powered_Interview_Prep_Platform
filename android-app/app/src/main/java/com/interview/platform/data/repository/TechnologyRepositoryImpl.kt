package com.interview.platform.data.repository

import com.interview.platform.data.local.dao.TechnologyDao
import com.interview.platform.data.remote.api.Mod04TechnologyManagementApiService
import com.interview.platform.domain.repository.TechnologyRepository
import com.interview.platform.domain.model.mod04_technology_management.Technology
import com.interview.platform.data.mapper.toDomain
import com.interview.platform.data.mapper.toEntity
import com.interview.platform.data.model.Result
import com.interview.platform.data.model.asResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class TechnologyRepositoryImpl @Inject constructor(
    private val apiService: Mod04TechnologyManagementApiService,
    private val dao: TechnologyDao
) : TechnologyRepository {

    override fun fetchAll(): Flow<Result<List<Technology>>> {
        return dao.getAll()
            .map { list -> list.map { it.toDomain() } }
            .asResult()
    }

    override suspend fun syncData(): Result<Unit> {
        return try {
            Timber.d("Syncing Technology data")
            Result.Success(Unit)
        } catch (e: Exception) {
            Timber.e(e, "Error syncing Technology")
            Result.Error(e)
        }
    }
}
