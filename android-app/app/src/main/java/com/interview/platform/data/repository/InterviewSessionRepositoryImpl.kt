package com.interview.platform.data.repository

import com.interview.platform.data.local.dao.InterviewSessionDao
import com.interview.platform.data.remote.api.Mod10InterviewSessionApiService
import com.interview.platform.domain.repository.InterviewSessionRepository
import com.interview.platform.domain.model.mod10_interview_session.InterviewSession
import com.interview.platform.data.mapper.toDomain
import com.interview.platform.data.mapper.toEntity
import com.interview.platform.data.model.Result
import com.interview.platform.data.model.asResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class InterviewSessionRepositoryImpl @Inject constructor(
    private val apiService: Mod10InterviewSessionApiService,
    private val dao: InterviewSessionDao
) : InterviewSessionRepository {

    override fun fetchAll(): Flow<Result<List<InterviewSession>>> {
        return dao.getAll()
            .map { list -> list.map { it.toDomain() } }
            .asResult()
    }

    override suspend fun syncData(): Result<Unit> {
        return try {
            Timber.d("Syncing InterviewSession data")
            Result.Success(Unit)
        } catch (e: Exception) {
            Timber.e(e, "Error syncing InterviewSession")
            Result.Error(e)
        }
    }
}
