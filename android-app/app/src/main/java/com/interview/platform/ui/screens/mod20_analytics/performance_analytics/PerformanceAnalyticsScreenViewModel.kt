package com.interview.platform.ui.screens.mod20_analytics.performance_analytics

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PerformanceAnalyticsScreenViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<PerformanceAnalyticsScreenUiState>(PerformanceAnalyticsScreenUiState.Empty)
    val uiState: StateFlow<PerformanceAnalyticsScreenUiState> = _uiState.asStateFlow()
    
    fun submitAction() {
        // Implementation placeholder
    }
}
