package com.interview.platform.ui.screens.mod01_authentication.otp_verification

sealed interface OtpVerificationScreenUiState {
    object Empty : OtpVerificationScreenUiState
    object Loading : OtpVerificationScreenUiState
    class Success() : OtpVerificationScreenUiState
    class Error(val message: String) : OtpVerificationScreenUiState
}
