package com.interview.platform.ui.screens.mod04_technology_management.technology_selection

sealed class TechnologySelectionScreenUiState {
    object Loading : TechnologySelectionScreenUiState()
    object Empty : TechnologySelectionScreenUiState()
    data class Success(val isLoading: Boolean = false, val defaultTechnology: String = "") : TechnologySelectionScreenUiState()
    data class Error(val message: String) : TechnologySelectionScreenUiState()
}
