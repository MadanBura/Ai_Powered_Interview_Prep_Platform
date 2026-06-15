package com.interview.platform.ui.screens.mod14_ai_evaluation.weakness_analysis

sealed class WeaknessAnalysisScreenUiState {
    object Loading : WeaknessAnalysisScreenUiState()
    object Empty : WeaknessAnalysisScreenUiState()
    data class WeaknessItem(
        val title: String,
        val description: String,
        val iconType: String // "Speed", "VoiceOverOff", "PriorityHigh"
    )

    data class Success(val weaknesses: List<WeaknessItem> = listOf(
        WeaknessItem("Pace of Speech", "Speaking rate was 165 wpm (Ideal: 130-150 wpm).", "Speed"),
        WeaknessItem("Filler Words", "Frequent use of \"um\" and \"like\" (14 occurrences detected).", "VoiceOverOff"),
        WeaknessItem("Technical Edge Cases", "Needs more depth on edge-case scenarios in technical architecture.", "PriorityHigh")
    )) : WeaknessAnalysisScreenUiState()
}
