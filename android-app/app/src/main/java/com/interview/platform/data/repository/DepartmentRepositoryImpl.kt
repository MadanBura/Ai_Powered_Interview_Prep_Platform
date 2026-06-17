package com.interview.platform.data.repository

import com.interview.platform.data.local.dao.DepartmentDao
import com.interview.platform.data.remote.api.Mod03DepartmentManagementApiService
import com.interview.platform.domain.repository.DepartmentRepository
import com.interview.platform.domain.model.mod03_department_management.Department
import com.interview.platform.data.mapper.toDomain
import com.interview.platform.data.mapper.toEntity
import com.interview.platform.data.model.Result
import com.interview.platform.data.model.asResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class DepartmentRepositoryImpl @Inject constructor(
    private val apiService: Mod03DepartmentManagementApiService,
    private val dao: DepartmentDao
) : DepartmentRepository {

    override fun fetchAll(): Flow<Result<List<Department>>> {
        return dao.getAll()
            .map { list -> list.map { it.toDomain() } }
            .asResult()
    }

    override suspend fun syncData(): Result<Unit> {
        return try {
            Timber.d("Syncing Department data")
            Result.Success(Unit)
        } catch (e: Exception) {
            Timber.e(e, "Error syncing Department")
            Result.Error(e)
        }
    }
}
