package com.interview.platform.domain.repository

import com.interview.platform.domain.model.mod11_question_delivery.QuestionDelivery
import com.interview.platform.data.model.Result
import kotlinx.coroutines.flow.Flow

interface QuestionDeliveryRepository {
    fun fetchAll(): Flow<Result<List<QuestionDelivery>>>
    suspend fun syncData(): Result<Unit>
}
