package com.interview.platform.domain.repository

import com.interview.platform.domain.model.mod03_department_management.Department
import com.interview.platform.data.model.Result
import kotlinx.coroutines.flow.Flow

interface DepartmentRepository {
    fun fetchAll(): Flow<Result<List<Department>>>
    suspend fun syncData(): Result<Unit>
}
