package com.interview.platform.ui.screens.mod02_user_profile.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

import androidx.lifecycle.viewModelScope
import com.interview.platform.data.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    val uiState: StateFlow<ProfileScreenUiState> = combine(
        userPreferencesRepository.userName,
        userPreferencesRepository.userEmail,
        userPreferencesRepository.userDepartment,
        userPreferencesRepository.userTechnologies,
        userPreferencesRepository.userExperienceLevel
    ) { name, email, department, technologies, expLevel ->
        ProfileScreenUiState.Success(
            UserProfileData(
                name = name ?: "Candidate",
                email = email ?: "candidate@example.com",
                department = department,
                technologies = technologies,
                experienceLevel = expLevel
            )
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ProfileScreenUiState.Loading
    )

    fun submitAction() {
        // Implementation placeholder
    }
}
