package com.interview.platform.data.local.dao

import androidx.room.*
import com.interview.platform.data.local.entity.mod13_speech_to_text.SpeechToTextEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SpeechToTextDao {
    @Query("SELECT * FROM mod13_speech_to_text_table")
    fun getAll(): Flow<List<SpeechToTextEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: SpeechToTextEntity)

    @Query("DELETE FROM mod13_speech_to_text_table")
    suspend fun deleteAll()
}
