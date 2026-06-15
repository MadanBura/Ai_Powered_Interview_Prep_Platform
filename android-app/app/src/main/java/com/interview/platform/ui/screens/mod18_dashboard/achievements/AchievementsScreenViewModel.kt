package com.interview.platform.ui.screens.mod18_dashboard.achievements

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AchievementsScreenViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<AchievementsScreenUiState>(AchievementsScreenUiState.Success())
    val uiState: StateFlow<AchievementsScreenUiState> = _uiState.asStateFlow()
    
    fun submitAction() {
        // Implementation placeholder
    }
}
