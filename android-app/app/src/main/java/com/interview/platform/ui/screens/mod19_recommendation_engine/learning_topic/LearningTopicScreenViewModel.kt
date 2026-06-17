package com.interview.platform.ui.screens.mod19_recommendation_engine.learning_topic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.platform.domain.repository.RecommendationRepository
import com.interview.platform.data.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class LearningTopicScreenUiState {
    object Loading : LearningTopicScreenUiState()
    data class Success(val title: String, val isCompleted: Boolean, val topicDetails: com.interview.platform.domain.model.mod19_recommendation_engine.RoadmapTopic? = null) : LearningTopicScreenUiState()
    data class Error(val message: String) : LearningTopicScreenUiState()
}

@HiltViewModel
class LearningTopicScreenViewModel @Inject constructor(
    private val recommendationRepository: RecommendationRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<LearningTopicScreenUiState>(LearningTopicScreenUiState.Loading)
    val uiState: StateFlow<LearningTopicScreenUiState> = _uiState.asStateFlow()

    private var currentRoadmapId: String = ""
    private var currentTopicName: String = ""
    private var topicDetails: Any? = null

    fun loadTopicDetails(roadmapId: String, topicName: String) {
        currentRoadmapId = roadmapId
        currentTopicName = topicName
        
        viewModelScope.launch {
            _uiState.value = LearningTopicScreenUiState.Loading
            when (val result = recommendationRepository.getTopic(topicName)) {
                is Result.Success -> {
                    topicDetails = result.data
                    _uiState.value = LearningTopicScreenUiState.Success(topicName, false, result.data)
                }
                is Result.Error -> {
                    _uiState.value = LearningTopicScreenUiState.Error(result.exception.message ?: "Failed to load topic")
                }
                is Result.Loading -> {
                    _uiState.value = LearningTopicScreenUiState.Loading
                }
            }
        }
    }

    fun completeTopic(onSuccess: () -> Unit) {
        viewModelScope.launch {
            _uiState.value = LearningTopicScreenUiState.Loading
            when (val result = recommendationRepository.completeTopic(currentRoadmapId, currentTopicName)) {
                is Result.Success -> {
                    _uiState.value = LearningTopicScreenUiState.Success(currentTopicName, true, topicDetails as? com.interview.platform.domain.model.mod19_recommendation_engine.RoadmapTopic)
                    onSuccess()
                }
                is Result.Error -> {
                    _uiState.value = LearningTopicScreenUiState.Error(result.exception.message ?: "Failed to complete topic")
                }
                is Result.Loading -> {
                    // Do nothing, already set to loading
                }
            }
        }
    }
}
