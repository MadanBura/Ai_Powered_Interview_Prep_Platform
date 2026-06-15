package com.interview.platform.domain.repository

import com.interview.platform.domain.model.mod14_ai_evaluation.AiEvaluation
import com.interview.platform.data.model.Result
import kotlinx.coroutines.flow.Flow

interface AiEvaluationRepository {
    fun fetchAll(): Flow<Result<List<AiEvaluation>>>
    suspend fun syncData(): Result<Unit>
}
