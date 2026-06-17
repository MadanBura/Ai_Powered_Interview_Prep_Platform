package com.interview.platform.ui.screens.mod19_recommendation_engine.interview_roadmap

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

import androidx.lifecycle.viewModelScope
import com.interview.platform.data.remote.api.Mod19RecommendationEngineApiService
import com.interview.platform.data.repository.UserPreferencesRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.firstOrNull

@HiltViewModel
class InterviewRoadmapScreenViewModel @Inject constructor(
    private val apiService: Mod19RecommendationEngineApiService,
    private val userPreferencesRepository: UserPreferencesRepository,
    private val recommendationRepository: com.interview.platform.domain.repository.RecommendationRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<InterviewRoadmapScreenUiState>(InterviewRoadmapScreenUiState.Loading)
    val uiState: StateFlow<InterviewRoadmapScreenUiState> = _uiState.asStateFlow()
    
    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            val role = userPreferencesRepository.userDepartment.firstOrNull() ?: ""
            val technology = userPreferencesRepository.userTechnologies.firstOrNull() ?: ""
            val experienceLevel = userPreferencesRepository.userExperienceLevel.firstOrNull() ?: ""

            if (technology.isEmpty() && role.isEmpty()) {
                _uiState.value = InterviewRoadmapScreenUiState.Empty
                return@launch
            }

            try {
                val activeRoadmapResult = recommendationRepository.getActiveRoadmap()
                var activeRoadmap: com.interview.platform.domain.model.mod19_recommendation_engine.UserRoadmap? = null
                if (activeRoadmapResult is com.interview.platform.data.model.Result.Success) {
                    activeRoadmap = activeRoadmapResult.data
                }

                val badgesResult = recommendationRepository.getUserBadges()
                var badges: List<com.interview.platform.domain.model.mod19_recommendation_engine.UserBadge> = emptyList()
                if (badgesResult is com.interview.platform.data.model.Result.Success) {
                    badges = badgesResult.data
                }

                if (activeRoadmap != null) {
                    // Fetch the roadmap template strictly linked to this active roadmap
                    val templateId = activeRoadmap.roadmapTemplateId
                    val roadmapResult = apiService.getRoadmapByTemplateId(templateId)
                    
                    if (roadmapResult.isSuccessful) {
                        val body = roadmapResult.body()
                        if (body != null && body.items.isNotEmpty()) {
                            _uiState.value = InterviewRoadmapScreenUiState.Success(body, activeRoadmap, badges)
                            return@launch
                        }
                    }
                }

                // Fallback to legacy fetching if no active roadmap exists (which shouldn't happen natively now)
                val roadmapResult = apiService.getRoadmap(role, technology, experienceLevel)
                if (roadmapResult.isSuccessful && activeRoadmap != null) {
                    val body = roadmapResult.body()
                    if (body != null && body.items.isNotEmpty()) {
                        _uiState.value = InterviewRoadmapScreenUiState.Success(body, activeRoadmap, badges)
                    } else {
                        _uiState.value = InterviewRoadmapScreenUiState.Empty
                    }
                } else {
                    _uiState.value = InterviewRoadmapScreenUiState.Empty
                }
            } catch (e: Exception) {
                _uiState.value = InterviewRoadmapScreenUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
