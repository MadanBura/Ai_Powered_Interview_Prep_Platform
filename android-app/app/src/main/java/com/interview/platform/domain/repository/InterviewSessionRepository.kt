package com.interview.platform.domain.repository

import com.interview.platform.domain.model.mod10_interview_session.InterviewSession
import com.interview.platform.data.model.Result
import kotlinx.coroutines.flow.Flow

interface InterviewSessionRepository {
    fun fetchAll(): Flow<Result<List<InterviewSession>>>
    suspend fun syncData(): Result<Unit>
}
