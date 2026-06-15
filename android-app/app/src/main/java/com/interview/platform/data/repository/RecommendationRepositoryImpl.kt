package com.interview.platform.data.repository

import com.interview.platform.data.local.dao.RecommendationDao
import com.interview.platform.data.remote.api.Mod19RecommendationEngineApiService
import com.interview.platform.domain.repository.RecommendationRepository
import com.interview.platform.domain.model.mod19_recommendation_engine.Recommendation
import com.interview.platform.data.mapper.toDomain
import com.interview.platform.data.mapper.toEntity
import com.interview.platform.data.model.Result
import com.interview.platform.data.model.asResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class RecommendationRepositoryImpl @Inject constructor(
    private val apiService: Mod19RecommendationEngineApiService
) : RecommendationRepository {

    override fun fetchAll(): Flow<Result<List<Recommendation>>> {
        return emptyFlow()
    }

    override suspend fun syncData(): Result<Unit> {
        return try {
            Timber.d("Syncing Recommendation data")
            Result.Success(Unit)
        } catch (e: Exception) {
            Timber.e(e, "Error syncing Recommendation")
            Result.Error(e)
        }
    }

    override suspend fun getActiveRoadmap(): Result<com.interview.platform.domain.model.mod19_recommendation_engine.UserRoadmap> {
        return try {
            val response = apiService.getActiveRoadmap()
            if (response.isSuccessful && response.body() != null) {
                Result.Success(response.body()!!)
            } else {
                Result.Error(Exception("Failed to load roadmap"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun startRoadmap(templateId: String?, techName: String?): Result<com.interview.platform.domain.model.mod19_recommendation_engine.UserRoadmap> {
        return try {
            val response = apiService.startRoadmap(templateId, techName)
            if (response.isSuccessful && response.body() != null) {
                Result.Success(response.body()!!)
            } else {
                Result.Error(Exception("Failed to start roadmap"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun completeTopic(roadmapId: String, topicName: String): Result<com.interview.platform.domain.model.mod19_recommendation_engine.UserRoadmap> {
        return try {
            val response = apiService.completeTopic(roadmapId, topicName)
            if (response.isSuccessful && response.body() != null) {
                Result.Success(response.body()!!)
            } else {
                Result.Error(Exception("Failed to complete topic"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getUserBadges(): Result<List<com.interview.platform.domain.model.mod19_recommendation_engine.UserBadge>> {
        return try {
            val response = apiService.getUserBadges()
            if (response.isSuccessful && response.body() != null) {
                Result.Success(response.body()!!)
            } else {
                Result.Error(Exception("Failed to load badges"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getTopic(title: String): Result<com.interview.platform.domain.model.mod19_recommendation_engine.RoadmapTopic> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getTopic(title)
            if (response.isSuccessful && response.body() != null) {
                Result.Success(response.body()!!)
            } else {
                Result.Error(Exception("Failed to fetch topic"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getAvailableRoadmaps(): Result<List<com.interview.platform.data.remote.dto.mod19_recommendation_engine.AvailableRoadmapDto>> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getAvailableRoadmaps()
            if (response.isSuccessful && response.body() != null) {
                Result.Success(response.body()!!)
            } else {
                Result.Error(Exception("Failed to fetch available roadmaps"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
