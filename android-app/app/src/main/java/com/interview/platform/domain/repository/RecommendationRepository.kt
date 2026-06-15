package com.interview.platform.domain.repository

import com.interview.platform.domain.model.mod19_recommendation_engine.Recommendation
import com.interview.platform.data.model.Result
import kotlinx.coroutines.flow.Flow

interface RecommendationRepository {
    fun fetchAll(): Flow<Result<List<Recommendation>>>
    suspend fun syncData(): Result<Unit>
    
    suspend fun getActiveRoadmap(): Result<com.interview.platform.domain.model.mod19_recommendation_engine.UserRoadmap>
    suspend fun startRoadmap(templateId: String? = null, techName: String? = null): Result<com.interview.platform.domain.model.mod19_recommendation_engine.UserRoadmap>
    suspend fun completeTopic(roadmapId: String, topicName: String): Result<com.interview.platform.domain.model.mod19_recommendation_engine.UserRoadmap>
    suspend fun getUserBadges(): Result<List<com.interview.platform.domain.model.mod19_recommendation_engine.UserBadge>>
    suspend fun getTopic(title: String): Result<com.interview.platform.domain.model.mod19_recommendation_engine.RoadmapTopic>
    suspend fun getAvailableRoadmaps(): Result<List<com.interview.platform.data.remote.dto.mod19_recommendation_engine.AvailableRoadmapDto>>
}
