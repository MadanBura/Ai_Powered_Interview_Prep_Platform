package com.interview.platform.domain.repository

import com.interview.platform.domain.model.mod02_user_profile.Profile
import com.interview.platform.data.model.Result
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun fetchAll(): Flow<Result<List<Profile>>>
    suspend fun syncData(): Result<Unit>
}
