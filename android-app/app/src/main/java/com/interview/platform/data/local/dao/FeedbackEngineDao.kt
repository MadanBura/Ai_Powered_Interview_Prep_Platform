package com.interview.platform.data.local.dao

import androidx.room.*
import com.interview.platform.data.local.entity.mod16_feedback_engine.FeedbackEngineEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FeedbackEngineDao {
    @Query("SELECT * FROM mod16_feedback_engine_table")
    fun getAll(): Flow<List<FeedbackEngineEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: FeedbackEngineEntity)

    @Query("DELETE FROM mod16_feedback_engine_table")
    suspend fun deleteAll()
}
