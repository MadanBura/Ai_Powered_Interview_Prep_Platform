package com.interview.platform.ui.screens.mod01_authentication.permissions

sealed class PermissionsScreenUiState {
    object Loading : PermissionsScreenUiState()
    object Empty : PermissionsScreenUiState()
    data class Success(val data: String = "") : PermissionsScreenUiState()
    data class Error(val message: String) : PermissionsScreenUiState()
}
