package com.interview.platform.ui.screens.mod02_user_profile.edit_profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class EditProfileScreenViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<EditProfileScreenUiState>(EditProfileScreenUiState.Success())
    val uiState: StateFlow<EditProfileScreenUiState> = _uiState.asStateFlow()
    
    fun submitAction(onSuccess: () -> Unit) {
        _uiState.value = EditProfileScreenUiState.Success(isLoading = true)
        // Simulate network call
        _uiState.value = EditProfileScreenUiState.Success(isLoading = false)
        onSuccess()
    }
}
