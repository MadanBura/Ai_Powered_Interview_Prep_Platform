package com.interview.platform.data.local.dao

import androidx.room.*
import com.interview.platform.data.local.entity.mod18_dashboard.DashboardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DashboardDao {
    @Query("SELECT * FROM mod18_dashboard_table")
    fun getAll(): Flow<List<DashboardEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: DashboardEntity)

    @Query("DELETE FROM mod18_dashboard_table")
    suspend fun deleteAll()
}
