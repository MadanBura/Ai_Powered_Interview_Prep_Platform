package com.interview.platform.domain.repository

import com.interview.platform.domain.model.mod15_scoring_engine.ScoringEngine
import com.interview.platform.data.model.Result
import kotlinx.coroutines.flow.Flow

interface ScoringEngineRepository {
    fun fetchAll(): Flow<Result<List<ScoringEngine>>>
    suspend fun syncData(): Result<Unit>
}
