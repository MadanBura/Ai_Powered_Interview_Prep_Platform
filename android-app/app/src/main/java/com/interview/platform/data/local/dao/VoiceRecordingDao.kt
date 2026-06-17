package com.interview.platform.data.local.dao

import androidx.room.*
import com.interview.platform.data.local.entity.mod12_voice_recording.VoiceRecordingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VoiceRecordingDao {
    @Query("SELECT * FROM mod12_voice_recording_table")
    fun getAll(): Flow<List<VoiceRecordingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: VoiceRecordingEntity)

    @Query("DELETE FROM mod12_voice_recording_table")
    suspend fun deleteAll()
}
