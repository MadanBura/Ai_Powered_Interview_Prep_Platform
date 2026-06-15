package com.interview.platform.ui.screens.mod02_user_profile.edit_profile

sealed class EditProfileScreenUiState {
    object Loading : EditProfileScreenUiState()
    object Empty : EditProfileScreenUiState()
    data class Success(val isLoading: Boolean = false) : EditProfileScreenUiState()
    data class Error(val message: String) : EditProfileScreenUiState()
}
