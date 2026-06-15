package com.interview.platform.domain.repository

import com.interview.platform.domain.model.mod17_reporting.Reporting
import com.interview.platform.data.model.Result
import kotlinx.coroutines.flow.Flow

interface ReportingRepository {
    fun fetchAll(): Flow<Result<List<Reporting>>>
    suspend fun syncData(): Result<Unit>
}
