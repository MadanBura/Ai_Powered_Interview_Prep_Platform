package com.interview.platform.data.local.dao

import androidx.room.*
import com.interview.platform.data.local.entity.mod01_authentication.AuthTokenEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AuthTokenDao {
    @Query("SELECT * FROM mod01_authentication_table")
    fun getAll(): Flow<List<AuthTokenEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: AuthTokenEntity)

    @Query("DELETE FROM mod01_authentication_table")
    suspend fun deleteAll()
}
