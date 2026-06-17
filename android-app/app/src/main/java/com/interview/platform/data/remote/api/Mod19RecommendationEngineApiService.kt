package com.interview.platform.data.remote.api

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import com.interview.platform.data.remote.dto.mod19_recommendation_engine.RecommendationResponseDto
import com.interview.platform.data.remote.dto.mod19_recommendation_engine.RoadmapResponseDto

interface Mod19RecommendationEngineApiService {

    @GET("/api/v1/candidates/recommendations")
    suspend fun retrieveRecommendations(): retrofit2.Response<RecommendationResponseDto>

    @GET("/api/v1/roadmaps/available")
    suspend fun getAvailableRoadmaps(): retrofit2.Response<List<com.interview.platform.data.remote.dto.mod19_recommendation_engine.AvailableRoadmapDto>>

    @GET("/api/v1/roadmaps")
    suspend fun getRoadmap(
        @Query("role") role: String,
        @Query("technology") technology: String,
        @Query("experienceLevel") experienceLevel: String
    ): retrofit2.Response<RoadmapResponseDto>

    @GET("/api/v1/roadmaps/{templateId}")
    suspend fun getRoadmapByTemplateId(
        @retrofit2.http.Path("templateId") templateId: String
    ): retrofit2.Response<RoadmapResponseDto>

    @GET("/api/v1/roadmaps/user/active")
    suspend fun getActiveRoadmap(): retrofit2.Response<com.interview.platform.domain.model.mod19_recommendation_engine.UserRoadmap>

    @POST("/api/v1/roadmaps/user/start")
    suspend fun startRoadmap(
        @Query("roadmapTemplateId") roadmapTemplateId: String? = null,
        @Query("techName") techName: String? = null
    ): retrofit2.Response<com.interview.platform.domain.model.mod19_recommendation_engine.UserRoadmap>

    @POST("/api/v1/roadmaps/user/{userRoadmapId}/topics/{topicName}/complete")
    suspend fun completeTopic(
        @retrofit2.http.Path("userRoadmapId") userRoadmapId: String,
        @retrofit2.http.Path("topicName") topicName: String
    ): retrofit2.Response<com.interview.platform.domain.model.mod19_recommendation_engine.UserRoadmap>

    @GET("/api/v1/badges/user")
    suspend fun getUserBadges(): retrofit2.Response<List<com.interview.platform.domain.model.mod19_recommendation_engine.UserBadge>>

    @GET("/api/v1/roadmaps/user/topics/{title}")
    suspend fun getTopic(@retrofit2.http.Path("title") title: String): retrofit2.Response<com.interview.platform.domain.model.mod19_recommendation_engine.RoadmapTopic>
}
