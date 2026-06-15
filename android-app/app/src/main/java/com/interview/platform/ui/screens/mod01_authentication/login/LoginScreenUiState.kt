package com.interview.platform.ui.screens.mod01_authentication.login

sealed interface LoginScreenUiState {
    object Empty : LoginScreenUiState
    object Loading : LoginScreenUiState
    class Success() : LoginScreenUiState
    class Error(val message: String) : LoginScreenUiState
}
