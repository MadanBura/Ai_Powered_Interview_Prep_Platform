package com.interview.platform.data.remote.dto.mod19_recommendation_engine

data class RoadmapResponseDto(
    val title: String,
    val items: List<RoadmapMilestoneDto>
)

data class RoadmapMilestoneDto(
    val title: String,
    val description: String,
    val status: String,
    val progress: Float,
    val topics: List<RoadmapTopicDto> = emptyList()
)

data class RoadmapTopicDto(
    val id: String,
    val name: String,
    val isCompleted: Boolean
)
