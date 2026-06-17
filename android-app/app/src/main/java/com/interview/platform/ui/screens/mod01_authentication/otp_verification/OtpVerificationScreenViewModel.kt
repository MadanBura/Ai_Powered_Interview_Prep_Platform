package com.interview.platform.ui.screens.mod01_authentication.otp_verification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.platform.data.remote.api.Mod01AuthenticationApiService
import com.interview.platform.data.remote.api.Mod02UserProfileApiService
import com.interview.platform.data.remote.TokenProvider
import com.interview.platform.data.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtpVerificationScreenViewModel @Inject constructor(
    private val authApi: Mod01AuthenticationApiService,
    private val profileApi: Mod02UserProfileApiService,
    private val userPreferencesRepository: UserPreferencesRepository,
    private val tokenProvider: TokenProvider
) : ViewModel() {
    private val _uiState = MutableStateFlow<OtpVerificationScreenUiState>(OtpVerificationScreenUiState.Empty)
    val uiState: StateFlow<OtpVerificationScreenUiState> = _uiState.asStateFlow()
    
    fun verifyOtp(email: String, otp: String) {
        if (otp.isBlank()) {
            _uiState.value = OtpVerificationScreenUiState.Error("OTP cannot be empty")
            return
        }
        _uiState.value = OtpVerificationScreenUiState.Loading
        viewModelScope.launch {
            try {
                val request = com.interview.platform.data.remote.dto.mod01_authentication.OtpVerifyDto(target = email, otpCode = otp)
                val response = authApi.verifyOtp(request)
                if (response.isSuccessful) {
                    val tokens = response.body()?.data
                    val accessToken = tokens?.accessToken
                    val refreshToken = tokens?.refreshToken
                    if (!accessToken.isNullOrEmpty()) {
                        // Set in-memory token so AuthInterceptor immediately uses it
                        tokenProvider.accessToken = accessToken
                        tokenProvider.refreshToken = refreshToken
                        // Persist both tokens in DataStore
                        userPreferencesRepository.setAuthToken(accessToken)
                        if (!refreshToken.isNullOrEmpty()) {
                            userPreferencesRepository.setRefreshToken(refreshToken)
                        }
                    }
                    
                    // Fetch profile to get name and email
                    try {
                        val profileResponse = profileApi.getProfile()
                        if (profileResponse.isSuccessful) {
                            val profile = profileResponse.body()?.data
                            profile?.name?.let { userPreferencesRepository.setUserName(it) }
                            profile?.email?.let { userPreferencesRepository.setUserEmail(it) }
                        }
                    } catch (e: Exception) {
                        // Ignore profile fetch failure so login still proceeds
                    }
                    
                    userPreferencesRepository.setLoggedIn(true)
                    _uiState.value = OtpVerificationScreenUiState.Success()
                } else if (response.code() == 401) {
                    _uiState.value = OtpVerificationScreenUiState.Error("Invalid OTP or expired")
                } else {
                    _uiState.value = OtpVerificationScreenUiState.Error("Server returned ${response.code()}")
                }
            } catch (e: Exception) {
                _uiState.value = OtpVerificationScreenUiState.Error(e.message ?: "Network Error")
            }
        }
    }
    
    fun resetState() {
        _uiState.value = OtpVerificationScreenUiState.Empty
    }
}
