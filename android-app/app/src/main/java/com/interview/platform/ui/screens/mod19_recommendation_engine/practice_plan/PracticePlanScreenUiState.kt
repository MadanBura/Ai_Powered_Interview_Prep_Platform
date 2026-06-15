package com.interview.platform.ui.screens.mod19_recommendation_engine.practice_plan

sealed class PracticePlanScreenUiState {
    object Loading : PracticePlanScreenUiState()
    object Empty : PracticePlanScreenUiState()
    data class Success(val data: String = "") : PracticePlanScreenUiState()
    data class Error(val message: String) : PracticePlanScreenUiState()
}
