package com.interview.platform.data.local.dao

import androidx.room.*
import com.interview.platform.data.local.entity.mod17_reporting.ReportingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReportingDao {
    @Query("SELECT * FROM mod17_reporting_table")
    fun getAll(): Flow<List<ReportingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ReportingEntity)

    @Query("DELETE FROM mod17_reporting_table")
    suspend fun deleteAll()
}
