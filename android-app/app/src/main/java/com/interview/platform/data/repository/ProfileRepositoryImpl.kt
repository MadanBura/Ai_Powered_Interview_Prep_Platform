package com.interview.platform.data.repository

import com.interview.platform.data.local.dao.CandidateProfileDao
import com.interview.platform.data.remote.api.Mod02UserProfileApiService
import com.interview.platform.domain.repository.ProfileRepository
import com.interview.platform.domain.model.mod02_user_profile.Profile
import com.interview.platform.data.mapper.toDomain
import com.interview.platform.data.mapper.toEntity
import com.interview.platform.data.model.Result
import com.interview.platform.data.model.asResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val apiService: Mod02UserProfileApiService,
    private val dao: CandidateProfileDao
) : ProfileRepository {

    override fun fetchAll(): Flow<Result<List<Profile>>> {
        return dao.getAll()
            .map { list -> list.map { it.toDomain() } }
            .asResult()
    }

    override suspend fun syncData(): Result<Unit> {
        return try {
            // Assuming first endpoint is the fetch for this generic impl
            Timber.d("Syncing Profile data")
            // val response = apiService.fetch(...) 
            // if (response.isSuccessful) { dao.insert(...) }
            Result.Success(Unit)
        } catch (e: Exception) {
            Timber.e(e, "Error syncing Profile")
            Result.Error(e)
        }
    }
}
