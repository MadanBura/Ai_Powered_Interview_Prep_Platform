package com.interview.platform.ui.screens.mod01_authentication.welcome

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class WelcomeScreenViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<WelcomeScreenUiState>(WelcomeScreenUiState.Success())
    val uiState: StateFlow<WelcomeScreenUiState> = _uiState.asStateFlow()
    
    fun submitAction() {
        // Implementation placeholder
    }
}
