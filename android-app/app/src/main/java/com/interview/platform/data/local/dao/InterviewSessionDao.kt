package com.interview.platform.data.local.dao

import androidx.room.*
import com.interview.platform.data.local.entity.mod10_interview_session.InterviewSessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InterviewSessionDao {
    @Query("SELECT * FROM mod10_interview_session_table")
    fun getAll(): Flow<List<InterviewSessionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: InterviewSessionEntity)

    @Query("DELETE FROM mod10_interview_session_table")
    suspend fun deleteAll()
}
