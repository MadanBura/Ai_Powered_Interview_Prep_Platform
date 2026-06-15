package com.interview.platform.ui.screens.mod19_recommendation_engine.learning_recommendations

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LearningRecommendationsScreenViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<LearningRecommendationsScreenUiState>(LearningRecommendationsScreenUiState.Empty)
    val uiState: StateFlow<LearningRecommendationsScreenUiState> = _uiState.asStateFlow()
    
    fun submitAction() {
        // Implementation placeholder
    }
}
