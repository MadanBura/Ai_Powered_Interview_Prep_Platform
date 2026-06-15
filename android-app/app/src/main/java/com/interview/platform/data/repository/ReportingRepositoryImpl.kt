package com.interview.platform.data.repository

import com.interview.platform.data.local.dao.ReportingDao
import com.interview.platform.data.remote.api.Mod17ReportingApiService
import com.interview.platform.domain.repository.ReportingRepository
import com.interview.platform.domain.model.mod17_reporting.Reporting
import com.interview.platform.data.mapper.toDomain
import com.interview.platform.data.mapper.toEntity
import com.interview.platform.data.model.Result
import com.interview.platform.data.model.asResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class ReportingRepositoryImpl @Inject constructor(
    private val apiService: Mod17ReportingApiService,
    private val dao: ReportingDao
) : ReportingRepository {

    override fun fetchAll(): Flow<Result<List<Reporting>>> {
        return dao.getAll()
            .map { list -> list.map { it.toDomain() } }
            .asResult()
    }

    override suspend fun syncData(): Result<Unit> {
        return try {
            Timber.d("Syncing Reporting data")
            Result.Success(Unit)
        } catch (e: Exception) {
            Timber.e(e, "Error syncing Reporting")
            Result.Error(e)
        }
    }
}
