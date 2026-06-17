package com.interview.platform.ui.screens.mod14_ai_evaluation.strength_analysis

sealed class StrengthAnalysisScreenUiState {
    object Loading : StrengthAnalysisScreenUiState()
    object Empty : StrengthAnalysisScreenUiState()
    data class StrengthItem(
        val title: String,
        val description: String,
        val iconType: String // "Verified", "Psychology", "Bolt"
    )

    data class Success(val strengths: List<StrengthItem> = listOf(
        StrengthItem("Structured Answers", "Consistent use of the STAR method across behavioral questions.", "Verified"),
        StrengthItem("Technical Depth", "Demonstrated deep understanding of system architecture trade-offs.", "Psychology"),
        StrengthItem("Confidence", "Very strong executive presence and decisive answering style.", "Bolt")
    )) : StrengthAnalysisScreenUiState()
}
