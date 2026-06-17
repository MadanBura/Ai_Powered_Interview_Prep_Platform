package com.interview.platform.domain.repository

import com.interview.platform.domain.model.mod09_interview_configuration.InterviewConfig
import com.interview.platform.data.model.Result
import kotlinx.coroutines.flow.Flow

interface InterviewConfigRepository {
    fun fetchAll(): Flow<Result<List<InterviewConfig>>>
    suspend fun syncData(): Result<Unit>
}
