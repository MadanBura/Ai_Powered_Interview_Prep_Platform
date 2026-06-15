package com.interview.platform.data.local.dao

import androidx.room.*
import com.interview.platform.data.local.entity.mod02_user_profile.CandidateProfileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CandidateProfileDao {
    @Query("SELECT * FROM mod02_user_profile_table")
    fun getAll(): Flow<List<CandidateProfileEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: CandidateProfileEntity)

    @Query("DELETE FROM mod02_user_profile_table")
    suspend fun deleteAll()
}
