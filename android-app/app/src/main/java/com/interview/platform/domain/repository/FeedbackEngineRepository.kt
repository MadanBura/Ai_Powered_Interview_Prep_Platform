package com.interview.platform.domain.repository

import com.interview.platform.domain.model.mod16_feedback_engine.FeedbackEngine
import com.interview.platform.data.model.Result
import kotlinx.coroutines.flow.Flow

interface FeedbackEngineRepository {
    fun fetchAll(): Flow<Result<List<FeedbackEngine>>>
    suspend fun syncData(): Result<Unit>
}
