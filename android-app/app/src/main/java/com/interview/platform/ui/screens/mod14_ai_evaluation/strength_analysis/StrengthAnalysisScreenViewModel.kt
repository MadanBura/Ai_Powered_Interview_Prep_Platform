package com.interview.platform.ui.screens.mod14_ai_evaluation.strength_analysis

import androidx.lifecycle.ViewModel
import com.interview.platform.data.repository.InterviewSessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class StrengthAnalysisScreenViewModel @Inject constructor(
    private val sessionManager: InterviewSessionManager
) : ViewModel() {
    private val _uiState = MutableStateFlow<StrengthAnalysisScreenUiState>(StrengthAnalysisScreenUiState.Empty)
    val uiState: StateFlow<StrengthAnalysisScreenUiState> = _uiState.asStateFlow()
    
    init {
        loadStrengths()
    }

    private fun loadStrengths() {
        val cached = sessionManager.evaluationResult
        if (cached != null && cached.overallFeedback.strengths.isNotEmpty()) {
            val items = cached.overallFeedback.strengths.map { str ->
                StrengthAnalysisScreenUiState.StrengthItem(
                    title = "Strength",
                    description = str,
                    iconType = listOf("Verified", "Psychology", "Bolt").random()
                )
            }
            _uiState.value = StrengthAnalysisScreenUiState.Success(strengths = items)
        } else {
            _uiState.value = StrengthAnalysisScreenUiState.Empty
        }
    }
}
