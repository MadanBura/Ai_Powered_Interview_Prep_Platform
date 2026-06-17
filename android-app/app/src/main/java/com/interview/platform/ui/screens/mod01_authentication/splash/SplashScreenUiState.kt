package com.interview.platform.ui.screens.mod01_authentication.splash

sealed class SplashScreenUiState {
    object Loading : SplashScreenUiState()
    object NavigateToHome : SplashScreenUiState()
    object NavigateToWelcome : SplashScreenUiState()
}
