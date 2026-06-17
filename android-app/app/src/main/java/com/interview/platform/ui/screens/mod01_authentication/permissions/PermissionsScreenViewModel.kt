package com.interview.platform.ui.screens.mod01_authentication.permissions

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PermissionsScreenViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<PermissionsScreenUiState>(PermissionsScreenUiState.Success())
    val uiState: StateFlow<PermissionsScreenUiState> = _uiState.asStateFlow()
    
    fun submitAction() {
        // Implementation placeholder
    }
}
