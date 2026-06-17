package com.interview.platform.data.remote.dto.mod14_ai_evaluation

data class AiEvaluationResponseDto(
    val success: Boolean,
    val data: AiEvaluationDataDto? = null,
    val error: Any? = null
)

data class AiEvaluationDataDto(
    val id: String? = null,
    val overallScore: Double? = null,
    val totalQuestions: Int? = null,
    val date: String? = null,
    val timeElapsed: String? = null,
    val categoryScores: CategoryScoresDto? = null,
    val questionAnalyses: List<QuestionAnalysisDto>? = null,
    val overallFeedback: OverallFeedbackDto? = null
)

data class CategoryScoresDto(
    val technicalAccuracy: Double? = null,
    val clarityAndCommunication: Double? = null,
    val problemSolving: Double? = null,
    val completeness: Double? = null
)

data class QuestionAnalysisDto(
    val questionId: String,
    val questionNumber: Int? = null,
    val questionText: String? = null,
    val expectedAnswer: String? = null,
    val providedAnswer: String? = null,
    val score: Double? = null,
    val isCorrect: Boolean? = null,
    val aiFeedback: String? = null,
    val keyConceptsHit: List<String>? = null,
    val keyConceptsMissed: List<String>? = null
)

data class OverallFeedbackDto(
    val strengths: List<String>? = null,
    val weaknesses: List<String>? = null,
    val actionableSteps: List<String>? = null
)
