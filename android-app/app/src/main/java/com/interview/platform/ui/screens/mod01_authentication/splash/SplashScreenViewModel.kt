package com.interview.platform.ui.screens.mod01_authentication.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.platform.data.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import com.interview.platform.data.remote.TokenProvider
import javax.inject.Inject
import kotlinx.coroutines.delay

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val tokenProvider: TokenProvider
) : ViewModel() {
    private val _uiState = MutableStateFlow<SplashScreenUiState>(SplashScreenUiState.Loading)
    val uiState: StateFlow<SplashScreenUiState> = _uiState.asStateFlow()
    
    init {
        checkAuthStatus()
    }

    private fun checkAuthStatus() {
        viewModelScope.launch {
            delay(3000) // Keep the artificial splash delay
            val isLoggedIn = userPreferencesRepository.isLoggedIn.first()
            if (isLoggedIn) {
                val token = userPreferencesRepository.authToken.first()
                if (token != null) {
                    tokenProvider.accessToken = token
                }
                _uiState.value = SplashScreenUiState.NavigateToHome
            } else {
                _uiState.value = SplashScreenUiState.NavigateToWelcome
            }
        }
    }
}
