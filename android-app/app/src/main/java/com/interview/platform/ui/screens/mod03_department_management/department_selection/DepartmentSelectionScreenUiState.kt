package com.interview.platform.ui.screens.mod03_department_management.department_selection

sealed class DepartmentSelectionScreenUiState {
    object Loading : DepartmentSelectionScreenUiState()
    object Empty : DepartmentSelectionScreenUiState()
    data class Success(val isLoading: Boolean = false) : DepartmentSelectionScreenUiState()
    data class Error(val message: String) : DepartmentSelectionScreenUiState()
}
