package com.interview.platform.ui.screens.mod04_technology_management.technology_selection

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

import androidx.lifecycle.viewModelScope
import com.interview.platform.data.remote.api.Mod02UserProfileApiService
import com.interview.platform.data.remote.dto.mod02_user_profile.ProfileRequestDto
import com.interview.platform.data.repository.InterviewSessionManager
import com.interview.platform.data.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

@HiltViewModel
class TechnologySelectionScreenViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val sessionManager: InterviewSessionManager,
    private val userProfileApiService: Mod02UserProfileApiService,
    private val technologyApiService: com.interview.platform.data.remote.api.Mod04TechnologyManagementApiService
) : ViewModel() {
    private val _uiState = MutableStateFlow<TechnologySelectionScreenUiState>(TechnologySelectionScreenUiState.Success())
    val uiState: StateFlow<TechnologySelectionScreenUiState> = _uiState.asStateFlow()
    
    init {
        viewModelScope.launch {
            val defaultTech = userPreferencesRepository.userTechnologies.firstOrNull() ?: ""
            _uiState.value = TechnologySelectionScreenUiState.Success(defaultTechnology = defaultTech)
        }
    }
    
    fun submitAction(technology: String, isOnboarding: Boolean, isFromProfile: Boolean = false, onSuccess: () -> Unit) {
        _uiState.value = TechnologySelectionScreenUiState.Success(isLoading = true)
        viewModelScope.launch {
            if (isOnboarding || isFromProfile) {
                userPreferencesRepository.setUserTechnologies(technology)
                val expLevel = userPreferencesRepository.userExperienceLevel.firstOrNull()
                try {
                    val req = ProfileRequestDto(technologies = technology, experienceLevel = expLevel)
                    val response = userProfileApiService.updateProfile(req)
                    _uiState.value = TechnologySelectionScreenUiState.Success(isLoading = false)
                    onSuccess()
                } catch (e: Exception) {
                    _uiState.value = TechnologySelectionScreenUiState.Success(isLoading = false)
                    onSuccess()
                }
            }
            if (!isOnboarding && !isFromProfile) {
                sessionManager.selectedTechnologyName = technology
                // Lookup the technology ID so the backend can filter questions correctly
                try {
                    val techResp = technologyApiService.listTechnologies()
                    if (techResp.isSuccessful) {
                        val matched = techResp.body()?.data?.technologies?.find {
                            it.name.equals(technology, ignoreCase = true)
                        }
                        if (matched != null) {
                            sessionManager.selectedTechnologyIds = listOf(matched.id)
                        }
                    }
                } catch (e: Exception) {
                    // If lookup fails, interview will still start but with fallback questions
                }
                _uiState.value = TechnologySelectionScreenUiState.Success(isLoading = false)
                onSuccess()
            }
        }
    }
}
