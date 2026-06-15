package com.interview.platform.ui.screens.mod10_interview_session.session

enum class SessionPhase {
    IDLE,
    RECORDING,
    REVIEW
}

sealed class InterviewSessionScreenUiState {
    object Loading : InterviewSessionScreenUiState()
    object Empty : InterviewSessionScreenUiState()
    object Completed : InterviewSessionScreenUiState()
    data class Success(
        val questionText: String = "",
        val questionHint: String = "Discuss patterns like Redux, Context API, or specialized libraries and why you choose them.",
        val topic: String = "Technical Question",
        val currentQuestionIndex: Int = 1,
        val totalQuestions: Int = 1,
        val remainingTimeSeconds: Int = 0,
        val totalTimeSeconds: Int = 60,
        val phase: SessionPhase = SessionPhase.IDLE,
        val recordingTimeSeconds: Int = 0,
        val answerText: String = ""
    ) : InterviewSessionScreenUiState()
    data class Error(val message: String) : InterviewSessionScreenUiState()
}
