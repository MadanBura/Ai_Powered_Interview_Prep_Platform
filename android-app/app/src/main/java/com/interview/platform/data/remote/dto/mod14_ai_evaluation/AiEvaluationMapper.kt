package com.interview.platform.data.remote.dto.mod14_ai_evaluation

import com.interview.platform.domain.model.mod14_ai_evaluation.*

fun AiEvaluationDataDto.toDomain(): EvaluationResult {
    return EvaluationResult(
        id = this.id ?: java.util.UUID.randomUUID().toString(),
        overallScore = this.overallScore ?: 0.0,
        totalQuestions = this.totalQuestions ?: 0,
        date = this.date ?: "N/A",
        timeElapsed = this.timeElapsed ?: "0:00",
        categoryScores = this.categoryScores?.toDomain() ?: CategoryScores(0.0, 0.0, 0.0, 0.0),
        questionAnalyses = this.questionAnalyses?.map { it.toDomain() } ?: emptyList(),
        overallFeedback = this.overallFeedback?.toDomain() ?: OverallFeedback(emptyList(), emptyList(), emptyList())
    )
}

fun CategoryScoresDto.toDomain(): CategoryScores {
    return CategoryScores(
        technicalAccuracy = this.technicalAccuracy ?: 0.0,
        clarityAndCommunication = this.clarityAndCommunication ?: 0.0,
        problemSolving = this.problemSolving ?: 0.0,
        completeness = this.completeness ?: 0.0
    )
}

fun QuestionAnalysisDto.toDomain(): QuestionAnalysis {
    return QuestionAnalysis(
        questionId = this.questionId,
        questionNumber = this.questionNumber ?: 1,
        questionText = this.questionText ?: "",
        expectedAnswer = this.expectedAnswer ?: "",
        providedAnswer = this.providedAnswer ?: "",
        score = this.score ?: 0.0,
        isCorrect = this.isCorrect ?: false,
        aiFeedback = this.aiFeedback ?: "",
        keyConceptsHit = this.keyConceptsHit ?: emptyList(),
        keyConceptsMissed = this.keyConceptsMissed ?: emptyList()
    )
}

fun OverallFeedbackDto.toDomain(): OverallFeedback {
    return OverallFeedback(
        strengths = this.strengths ?: emptyList(),
        weaknesses = this.weaknesses ?: emptyList(),
        actionableSteps = this.actionableSteps ?: emptyList()
    )
}
