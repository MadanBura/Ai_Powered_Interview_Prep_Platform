package com.interview.platform.data.local.dao

import androidx.room.*
import com.interview.platform.data.local.entity.mod05_experience_level_management.ExperienceLevelEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExperienceLevelDao {
    @Query("SELECT * FROM mod05_experience_level_management_table")
    fun getAll(): Flow<List<ExperienceLevelEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ExperienceLevelEntity)

    @Query("DELETE FROM mod05_experience_level_management_table")
    suspend fun deleteAll()
}
