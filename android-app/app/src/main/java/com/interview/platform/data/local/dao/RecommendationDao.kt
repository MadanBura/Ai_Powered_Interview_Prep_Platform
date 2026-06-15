package com.interview.platform.data.local.dao

import androidx.room.*
import com.interview.platform.data.local.entity.mod19_recommendation_engine.RecommendationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecommendationDao {
    @Query("SELECT * FROM mod19_recommendation_engine_table")
    fun getAll(): Flow<List<RecommendationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: RecommendationEntity)

    @Query("DELETE FROM mod19_recommendation_engine_table")
    suspend fun deleteAll()
}
