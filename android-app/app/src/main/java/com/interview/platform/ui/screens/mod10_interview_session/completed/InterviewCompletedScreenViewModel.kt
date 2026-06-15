package com.interview.platform.ui.screens.mod10_interview_session.completed

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class InterviewCompletedScreenViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<InterviewCompletedScreenUiState>(InterviewCompletedScreenUiState.Success())
    val uiState: StateFlow<InterviewCompletedScreenUiState> = _uiState.asStateFlow()
    
    fun submitAction(onSuccess: () -> Unit) {
        onSuccess()
    }
}
