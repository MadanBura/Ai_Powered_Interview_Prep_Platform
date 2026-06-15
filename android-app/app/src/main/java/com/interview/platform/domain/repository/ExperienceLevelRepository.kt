package com.interview.platform.domain.repository

import com.interview.platform.domain.model.mod05_experience_level_management.ExperienceLevel
import com.interview.platform.data.model.Result
import kotlinx.coroutines.flow.Flow

interface ExperienceLevelRepository {
    fun fetchAll(): Flow<Result<List<ExperienceLevel>>>
    suspend fun syncData(): Result<Unit>
}
