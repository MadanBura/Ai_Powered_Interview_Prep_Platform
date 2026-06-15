package com.interview.platform.domain.repository

import com.interview.platform.domain.model.mod20_analytics.Analytics
import com.interview.platform.data.model.Result
import kotlinx.coroutines.flow.Flow

interface AnalyticsRepository {
    fun fetchAll(): Flow<Result<List<Analytics>>>
    suspend fun syncData(): Result<Unit>
}
