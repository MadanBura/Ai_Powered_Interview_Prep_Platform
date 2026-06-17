package com.interview.platform.data.local.dao

import androidx.room.*
import com.interview.platform.data.local.entity.mod15_scoring_engine.ScoringEngineEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScoringEngineDao {
    @Query("SELECT * FROM mod15_scoring_engine_table")
    fun getAll(): Flow<List<ScoringEngineEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ScoringEngineEntity)

    @Query("DELETE FROM mod15_scoring_engine_table")
    suspend fun deleteAll()
}
