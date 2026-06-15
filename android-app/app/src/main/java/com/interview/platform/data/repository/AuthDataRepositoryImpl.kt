package com.interview.platform.data.repository

import com.interview.platform.data.local.dao.AuthTokenDao
import com.interview.platform.data.remote.api.Mod01AuthenticationApiService
import com.interview.platform.domain.repository.AuthDataRepository
import com.interview.platform.domain.model.mod01_authentication.AuthData
import com.interview.platform.data.mapper.toDomain
import com.interview.platform.data.mapper.toEntity
import com.interview.platform.data.model.Result
import com.interview.platform.data.model.asResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class AuthDataRepositoryImpl @Inject constructor(
    private val apiService: Mod01AuthenticationApiService,
    private val dao: AuthTokenDao
) : AuthDataRepository {

    override fun fetchAll(): Flow<Result<List<AuthData>>> {
        return dao.getAll()
            .map { list -> list.map { it.toDomain() } }
            .asResult()
    }

    override suspend fun syncData(): Result<Unit> {
        return try {
            // Assuming first endpoint is the fetch for this generic impl
            Timber.d("Syncing AuthData data")
            // val response = apiService.fetch(...) 
            // if (response.isSuccessful) { dao.insert(...) }
            Result.Success(Unit)
        } catch (e: Exception) {
            Timber.e(e, "Error syncing AuthData")
            Result.Error(e)
        }
    }
}
