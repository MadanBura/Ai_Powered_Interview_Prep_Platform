package com.interview.platform.ui.screens.mod03_department_management.department_selection

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DepartmentSelectionScreenViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<DepartmentSelectionScreenUiState>(DepartmentSelectionScreenUiState.Success())
    val uiState: StateFlow<DepartmentSelectionScreenUiState> = _uiState.asStateFlow()
    
    fun submitAction(department: String, onSuccess: () -> Unit) {
        _uiState.value = DepartmentSelectionScreenUiState.Success(isLoading = true)
        // Store selected department in a local session config or repository
        _uiState.value = DepartmentSelectionScreenUiState.Success(isLoading = false)
        onSuccess()
    }
}
