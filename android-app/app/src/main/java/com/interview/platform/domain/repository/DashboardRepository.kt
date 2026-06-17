package com.interview.platform.domain.repository

import com.interview.platform.domain.model.mod18_dashboard.Dashboard
import com.interview.platform.data.model.Result
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {
    fun fetchAll(): Flow<Result<List<Dashboard>>>
    suspend fun syncData(): Result<Unit>
}
