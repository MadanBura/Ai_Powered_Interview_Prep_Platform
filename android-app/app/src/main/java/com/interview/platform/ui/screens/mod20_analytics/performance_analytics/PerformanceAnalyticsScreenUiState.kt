package com.interview.platform.ui.screens.mod20_analytics.performance_analytics

sealed class PerformanceAnalyticsScreenUiState {
    object Loading : PerformanceAnalyticsScreenUiState()
    object Empty : PerformanceAnalyticsScreenUiState()
    data class DailyScore(val day: String, val progress: Float, val isHighlighted: Boolean = false)
    data class CategoryScore(val title: String, val iconType: String, val score: String, val progress: Float, val feedback: String)

    data class Success(
        val momentumTrend: String = "+12% vs LW",
        val dailyScores: List<DailyScore> = listOf(
            DailyScore("MON", 0.45f),
            DailyScore("TUE", 0.60f),
            DailyScore("WED", 0.55f),
            DailyScore("THU", 0.78f),
            DailyScore("FRI", 0.82f),
            DailyScore("SAT", 0.90f, true),
            DailyScore("SUN", 0.10f)
        ),
        val lastWeekAverage: String = "74%",
        val currentWeekAverage: String = "86%",
        val weeklyInsight: String = "\"Your technical accuracy has improved by 18 points this week.\"",
        val sessionsToday: String = "04",
        val categories: List<CategoryScore> = listOf(
            CategoryScore("Technical", "Terminal", "88", 0.88f, "Strong logic skills. Focus more on complexity analysis in future sessions."),
            CategoryScore("Communication", "RecordVoiceOver", "72", 0.72f, "Good pacing. Try to reduce filler words like \"um\" and \"actually\"."),
            CategoryScore("Confidence", "Psychology", "94", 0.94f, "Excellent presence and eye contact. You seem very comfortable with the topics.")
        ),
        val milestoneTitle: String = "Next Milestone: Expert Level",
        val milestoneDesc: String = "Based on your current trajectory, you are only 5 technical interviews away from reaching the \"Expert\" tier. We recommend focusing on Distributed Systems next week."
    ) : PerformanceAnalyticsScreenUiState()
}
