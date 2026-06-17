package com.interview.platform.ui.screens.mod19_recommendation_engine.recommended_technologies

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

import androidx.lifecycle.viewModelScope
import com.interview.platform.domain.repository.RecommendationRepository
import kotlinx.coroutines.launch

@HiltViewModel
class RecommendedTechnologiesScreenViewModel @Inject constructor(
    private val recommendationRepository: RecommendationRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<RecommendedTechnologiesScreenUiState>(RecommendedTechnologiesScreenUiState.Loading)
    val uiState: StateFlow<RecommendedTechnologiesScreenUiState> = _uiState.asStateFlow()
    
    init {
        fetchAvailableRoadmaps()
    }

    private fun fetchAvailableRoadmaps() {
        viewModelScope.launch {
            _uiState.value = RecommendedTechnologiesScreenUiState.Loading
            val result = recommendationRepository.getAvailableRoadmaps()
            if (result is com.interview.platform.data.model.Result.Success) {
                if (result.data.isEmpty()) {
                    _uiState.value = RecommendedTechnologiesScreenUiState.Empty
                } else {
                    _uiState.value = RecommendedTechnologiesScreenUiState.Success(result.data)
                }
            } else {
                _uiState.value = RecommendedTechnologiesScreenUiState.Error("Failed to fetch roadmaps")
            }
        }
    }

    fun startPath(techName: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            val result = recommendationRepository.startRoadmap(null, techName)
            if (result is com.interview.platform.data.model.Result.Success) {
                onSuccess()
            }
        }
    }
}
