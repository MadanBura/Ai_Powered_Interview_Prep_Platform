package com.interview.platform.domain.repository

import com.interview.platform.domain.model.mod01_authentication.AuthData
import com.interview.platform.data.model.Result
import kotlinx.coroutines.flow.Flow

interface AuthDataRepository {
    fun fetchAll(): Flow<Result<List<AuthData>>>
    suspend fun syncData(): Result<Unit>
}
