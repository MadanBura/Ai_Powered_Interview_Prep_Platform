package com.interview.platform.data.local.dao

import androidx.room.*
import com.interview.platform.data.local.entity.mod03_department_management.DepartmentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DepartmentDao {
    @Query("SELECT * FROM mod03_department_management_table")
    fun getAll(): Flow<List<DepartmentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: DepartmentEntity)

    @Query("DELETE FROM mod03_department_management_table")
    suspend fun deleteAll()
}
