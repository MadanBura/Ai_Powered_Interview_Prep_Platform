package com.interview.platform.ui.screens.mod01_authentication.welcome

sealed class WelcomeScreenUiState {
    object Loading : WelcomeScreenUiState()
    object Empty : WelcomeScreenUiState()
    data class Success(val data: String = "") : WelcomeScreenUiState()
    data class Error(val message: String) : WelcomeScreenUiState()
}
