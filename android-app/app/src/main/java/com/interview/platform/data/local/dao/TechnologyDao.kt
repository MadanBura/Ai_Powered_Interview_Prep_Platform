package com.interview.platform.data.local.dao

import androidx.room.*
import com.interview.platform.data.local.entity.mod04_technology_management.TechnologyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TechnologyDao {
    @Query("SELECT * FROM mod04_technology_management_table")
    fun getAll(): Flow<List<TechnologyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: TechnologyEntity)

    @Query("DELETE FROM mod04_technology_management_table")
    suspend fun deleteAll()
}
