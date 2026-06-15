package com.interview.platform.domain.repository

import com.interview.platform.domain.model.mod04_technology_management.Technology
import com.interview.platform.data.model.Result
import kotlinx.coroutines.flow.Flow

interface TechnologyRepository {
    fun fetchAll(): Flow<Result<List<Technology>>>
    suspend fun syncData(): Result<Unit>
}
