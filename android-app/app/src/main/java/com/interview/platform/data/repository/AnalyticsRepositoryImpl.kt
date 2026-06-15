package com.interview.platform.data.repository

import com.interview.platform.data.local.dao.AnalyticsDao
import com.interview.platform.data.remote.api.Mod20AnalyticsApiService
import com.interview.platform.domain.repository.AnalyticsRepository
import com.interview.platform.domain.model.mod20_analytics.Analytics
import com.interview.platform.data.mapper.toDomain
import com.interview.platform.data.mapper.toEntity
import com.interview.platform.data.model.Result
import com.interview.platform.data.model.asResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class AnalyticsRepositoryImpl @Inject constructor(
    private val apiService: Mod20AnalyticsApiService,
    private val dao: AnalyticsDao
) : AnalyticsRepository {

    override fun fetchAll(): Flow<Result<List<Analytics>>> {
        return dao.getAll()
            .map { list -> list.map { it.toDomain() } }
            .asResult()
    }

    override suspend fun syncData(): Result<Unit> {
        return try {
            Timber.d("Syncing Analytics data")
            Result.Success(Unit)
        } catch (e: Exception) {
            Timber.e(e, "Error syncing Analytics")
            Result.Error(e)
        }
    }
}
