package com.interview.platform.ui.screens.mod02_user_profile.profile

sealed class ProfileScreenUiState {
    object Loading : ProfileScreenUiState()
    object Empty : ProfileScreenUiState()
    data class Success(val data: UserProfileData) : ProfileScreenUiState()
    data class Error(val message: String) : ProfileScreenUiState()
}

data class UserProfileData(
    val name: String,
    val email: String,
    val department: String? = null,
    val technologies: String? = null,
    val experienceLevel: String? = null
)
