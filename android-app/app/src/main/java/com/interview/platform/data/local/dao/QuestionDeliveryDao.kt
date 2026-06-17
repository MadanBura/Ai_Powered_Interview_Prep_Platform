package com.interview.platform.data.local.dao

import androidx.room.*
import com.interview.platform.data.local.entity.mod11_question_delivery.QuestionDeliveryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDeliveryDao {
    @Query("SELECT * FROM mod11_question_delivery_table")
    fun getAll(): Flow<List<QuestionDeliveryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: QuestionDeliveryEntity)

    @Query("DELETE FROM mod11_question_delivery_table")
    suspend fun deleteAll()
}
