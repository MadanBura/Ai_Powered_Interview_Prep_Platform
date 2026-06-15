package com.interview.platform.data.local.dao

import androidx.room.*
import com.interview.platform.data.local.entity.mod14_ai_evaluation.AiEvaluationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AiEvaluationDao {
    @Query("SELECT * FROM mod14_ai_evaluation_table")
    fun getAll(): Flow<List<AiEvaluationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: AiEvaluationEntity)

    @Query("DELETE FROM mod14_ai_evaluation_table")
    suspend fun deleteAll()
}
