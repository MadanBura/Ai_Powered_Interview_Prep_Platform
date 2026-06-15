package com.interview.platform.data.repository

import com.interview.platform.data.local.dao.QuestionDeliveryDao
import com.interview.platform.data.remote.api.Mod11QuestionDeliveryApiService
import com.interview.platform.domain.repository.QuestionDeliveryRepository
import com.interview.platform.domain.model.mod11_question_delivery.QuestionDelivery
import com.interview.platform.data.mapper.toDomain
import com.interview.platform.data.mapper.toEntity
import com.interview.platform.data.model.Result
import com.interview.platform.data.model.asResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class QuestionDeliveryRepositoryImpl @Inject constructor(
    private val apiService: Mod11QuestionDeliveryApiService,
    private val dao: QuestionDeliveryDao
) : QuestionDeliveryRepository {

    override fun fetchAll(): Flow<Result<List<QuestionDelivery>>> {
        return dao.getAll()
            .map { list -> list.map { it.toDomain() } }
            .asResult()
    }

    override suspend fun syncData(): Result<Unit> {
        return try {
            Timber.d("Syncing QuestionDelivery data")
            Result.Success(Unit)
        } catch (e: Exception) {
            Timber.e(e, "Error syncing QuestionDelivery")
            Result.Error(e)
        }
    }
}
