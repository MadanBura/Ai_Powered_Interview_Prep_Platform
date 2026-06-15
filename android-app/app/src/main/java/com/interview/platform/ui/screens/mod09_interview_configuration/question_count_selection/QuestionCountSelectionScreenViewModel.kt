package com.interview.platform.ui.screens.mod09_interview_configuration.question_count_selection

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

import androidx.lifecycle.viewModelScope
import com.interview.platform.data.repository.InterviewSessionManager
import kotlinx.coroutines.launch

@HiltViewModel
class QuestionCountSelectionScreenViewModel @Inject constructor(
    private val sessionManager: InterviewSessionManager
) : ViewModel() {
    private val _uiState = MutableStateFlow<QuestionCountSelectionScreenUiState>(QuestionCountSelectionScreenUiState.Success())
    val uiState: StateFlow<QuestionCountSelectionScreenUiState> = _uiState.asStateFlow()
    
    fun submitAction(questionCount: Int, onSuccess: () -> Unit) {
        viewModelScope.launch {
            sessionManager.questionCount = questionCount
            onSuccess()
        }
    }
}
