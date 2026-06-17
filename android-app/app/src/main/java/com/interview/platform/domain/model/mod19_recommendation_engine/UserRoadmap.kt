package com.interview.platform.domain.model.mod19_recommendation_engine

data class UserRoadmap(
    val id: String,
    val userId: String,
    val roadmapTemplateId: String,
    val status: String,
    val progressPercentage: Int,
    val completedTopics: List<String>,
    val startedAt: String?
)
