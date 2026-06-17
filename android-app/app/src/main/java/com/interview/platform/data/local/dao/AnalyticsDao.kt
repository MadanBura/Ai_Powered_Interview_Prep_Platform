package com.interview.platform.data.local.dao

import androidx.room.*
import com.interview.platform.data.local.entity.mod20_analytics.AnalyticsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnalyticsDao {
    @Query("SELECT * FROM mod20_analytics_table")
    fun getAll(): Flow<List<AnalyticsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: AnalyticsEntity)

    @Query("DELETE FROM mod20_analytics_table")
    suspend fun deleteAll()
}
