package com.interview.platform.data.repository

import com.interview.platform.data.local.dao.DashboardDao
import com.interview.platform.data.remote.api.Mod18DashboardApiService
import com.interview.platform.domain.repository.DashboardRepository
import com.interview.platform.domain.model.mod18_dashboard.Dashboard
import com.interview.platform.data.mapper.toDomain
import com.interview.platform.data.mapper.toEntity
import com.interview.platform.data.model.Result
import com.interview.platform.data.model.asResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor(
    private val apiService: Mod18DashboardApiService,
    private val dao: DashboardDao
) : DashboardRepository {

    override fun fetchAll(): Flow<Result<List<Dashboard>>> {
        return dao.getAll()
            .map { list -> list.map { it.toDomain() } }
            .asResult()
    }

    override suspend fun syncData(): Result<Unit> {
        return try {
            Timber.d("Syncing Dashboard data")
            Result.Success(Unit)
        } catch (e: Exception) {
            Timber.e(e, "Error syncing Dashboard")
            Result.Error(e)
        }
    }
}
