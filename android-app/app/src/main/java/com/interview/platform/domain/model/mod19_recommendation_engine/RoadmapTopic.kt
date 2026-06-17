package com.interview.platform.domain.model.mod19_recommendation_engine

data class RoadmapTopic(
    val title: String,
    val subtopics: List<String>,
    val codeSnippet: String,
    val pros: List<String>,
    val cons: List<String>,
    val useCases: List<String>,
    val tags: List<String>
)
