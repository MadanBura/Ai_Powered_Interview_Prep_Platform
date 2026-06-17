package com.interview.platform.ui.screens.mod11_question_delivery.question_submitted

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class QuestionSubmittedScreenViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<QuestionSubmittedScreenUiState>(QuestionSubmittedScreenUiState.Success())
    val uiState: StateFlow<QuestionSubmittedScreenUiState> = _uiState.asStateFlow()
    
    fun submitAction(onSuccess: () -> Unit) {
        onSuccess()
    }
}
