package com.interview.platform.ui.screens.mod16_feedback_engine.ai_feedback

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.platform.data.repository.InterviewSessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AiFeedbackReportScreenViewModel @Inject constructor(
    private val sessionManager: InterviewSessionManager
) : ViewModel() {
    private val _uiState = MutableStateFlow<AiFeedbackReportScreenUiState>(AiFeedbackReportScreenUiState.Loading)
    val uiState: StateFlow<AiFeedbackReportScreenUiState> = _uiState.asStateFlow()
    
    init {
        loadReport()
    }

    private fun loadReport() {
        val cached = sessionManager.evaluationResult
        if (cached != null) {
            _uiState.value = AiFeedbackReportScreenUiState.Success(result = cached)
        } else {
            _uiState.value = AiFeedbackReportScreenUiState.Error("No evaluation result available")
        }
    }
}
