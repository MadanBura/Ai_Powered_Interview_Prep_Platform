package com.interview.platform.ui.screens.mod05_experience_level_management.experience_selection

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

import androidx.lifecycle.viewModelScope
import com.interview.platform.data.repository.InterviewSessionManager
import com.interview.platform.data.repository.UserPreferencesRepository
import kotlinx.coroutines.launch

import kotlinx.coroutines.flow.firstOrNull

@HiltViewModel
class ExperienceSelectionScreenViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val sessionManager: InterviewSessionManager
) : ViewModel() {
    private val _uiState = MutableStateFlow<ExperienceSelectionScreenUiState>(ExperienceSelectionScreenUiState.Success())
    val uiState: StateFlow<ExperienceSelectionScreenUiState> = _uiState.asStateFlow()
    
    init {
        viewModelScope.launch {
            val defaultExp = userPreferencesRepository.userExperienceLevel.firstOrNull() ?: ""
            _uiState.value = ExperienceSelectionScreenUiState.Success(defaultExperience = defaultExp)
        }
    }

    fun submitAction(level: String, isOnboarding: Boolean, isFromProfile: Boolean = false, onSuccess: () -> Unit) {
        _uiState.value = ExperienceSelectionScreenUiState.Success(isLoading = true)
        viewModelScope.launch {
            if (isOnboarding || isFromProfile) {
                userPreferencesRepository.setUserExperienceLevel(level)
            }
            if (!isOnboarding && !isFromProfile) {
                sessionManager.selectedExperienceLevelName = level
            }
            _uiState.value = ExperienceSelectionScreenUiState.Success(isLoading = false)
            onSuccess()
        }
    }
}
