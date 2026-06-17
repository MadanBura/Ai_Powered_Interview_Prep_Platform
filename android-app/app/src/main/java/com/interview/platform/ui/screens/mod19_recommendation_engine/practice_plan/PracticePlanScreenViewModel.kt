package com.interview.platform.ui.screens.mod19_recommendation_engine.practice_plan

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PracticePlanScreenViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<PracticePlanScreenUiState>(PracticePlanScreenUiState.Empty)
    val uiState: StateFlow<PracticePlanScreenUiState> = _uiState.asStateFlow()
    
    fun submitAction() {
        // Implementation placeholder
    }
}
