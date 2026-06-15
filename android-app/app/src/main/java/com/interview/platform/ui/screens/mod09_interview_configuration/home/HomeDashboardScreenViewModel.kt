package com.interview.platform.ui.screens.mod09_interview_configuration.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeDashboardScreenViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<HomeDashboardScreenUiState>(HomeDashboardScreenUiState.Success())
    val uiState: StateFlow<HomeDashboardScreenUiState> = _uiState.asStateFlow()
    
    fun submitAction() {
        // Implementation placeholder
    }
}
