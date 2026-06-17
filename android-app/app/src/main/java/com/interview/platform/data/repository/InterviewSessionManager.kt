package com.interview.platform.data.repository

import com.interview.platform.data.remote.dto.mod11_question_delivery.QuestionItemDto
import com.interview.platform.domain.model.mod14_ai_evaluation.EvaluationResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InterviewSessionManager @Inject constructor() {
    var selectedDepartmentId: String? = null
    var selectedTechnologyIds: List<String> = emptyList()
    var selectedTechnologyName: String? = null
    var selectedExperienceLevelId: String? = null
    var selectedExperienceLevelName: String? = null
    var questionCount: Int = 10
    
    var currentSessionId: String? = null
    
    var sessionQuestions: List<QuestionItemDto> = emptyList()
    var currentQuestionIndex: Int = 0
    
    var evaluationResult: EvaluationResult? = null
    
    val currentQuestion: QuestionItemDto?
        get() = sessionQuestions.getOrNull(currentQuestionIndex)
    
    fun clearConfig() {
        selectedDepartmentId = null
        selectedTechnologyIds = emptyList()
        selectedExperienceLevelId = null
    }
    
    fun clearSession() {
        currentSessionId = null
        sessionQuestions = emptyList()
        currentQuestionIndex = 0
        evaluationResult = null
    }
}
