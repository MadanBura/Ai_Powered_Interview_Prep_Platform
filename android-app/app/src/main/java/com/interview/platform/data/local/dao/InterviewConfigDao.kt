package com.interview.platform.data.local.dao

import androidx.room.*
import com.interview.platform.data.local.entity.mod09_interview_configuration.InterviewConfigEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InterviewConfigDao {
    @Query("SELECT * FROM mod09_interview_configuration_table")
    fun getAll(): Flow<List<InterviewConfigEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: InterviewConfigEntity)

    @Query("DELETE FROM mod09_interview_configuration_table")
    suspend fun deleteAll()
}
