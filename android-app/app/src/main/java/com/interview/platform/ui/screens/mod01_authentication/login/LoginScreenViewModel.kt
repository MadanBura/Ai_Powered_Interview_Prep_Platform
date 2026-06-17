package com.interview.platform.ui.screens.mod01_authentication.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.platform.data.remote.api.Mod01AuthenticationApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val authApi: Mod01AuthenticationApiService
) : ViewModel() {
    private val _uiState = MutableStateFlow<LoginScreenUiState>(LoginScreenUiState.Empty)
    val uiState: StateFlow<LoginScreenUiState> = _uiState.asStateFlow()
    
    fun requestOtp(email: String) {
        if (email.isBlank()) {
            _uiState.value = LoginScreenUiState.Error("Email cannot be empty")
            return
        }
        
        _uiState.value = LoginScreenUiState.Loading
        viewModelScope.launch {
            try {
                val request = com.interview.platform.data.remote.dto.mod01_authentication.OtpRequestDto(target = email)
                val response = authApi.requestOtp(request)
                if (response.isSuccessful) {
                    _uiState.value = LoginScreenUiState.Success()
                } else if (response.code() == 401) {
                    _uiState.value = LoginScreenUiState.Error("Email not found in database. Contact administrator.")
                } else {
                    _uiState.value = LoginScreenUiState.Error("Server returned ${response.code()}")
                }
            } catch (e: Exception) {
                _uiState.value = LoginScreenUiState.Error(e.message ?: "Network Error")
            }
        }
    }
    
    fun resetState() {
        _uiState.value = LoginScreenUiState.Empty
    }
}
