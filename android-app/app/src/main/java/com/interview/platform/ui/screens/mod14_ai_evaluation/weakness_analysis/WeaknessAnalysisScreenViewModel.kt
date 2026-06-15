package com.interview.platform.ui.screens.mod14_ai_evaluation.weakness_analysis

import androidx.lifecycle.ViewModel
import com.interview.platform.data.repository.InterviewSessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class WeaknessAnalysisScreenViewModel @Inject constructor(
    private val sessionManager: InterviewSessionManager
) : ViewModel() {
    private val _uiState = MutableStateFlow<WeaknessAnalysisScreenUiState>(WeaknessAnalysisScreenUiState.Empty)
    val uiState: StateFlow<WeaknessAnalysisScreenUiState> = _uiState.asStateFlow()
    
    init {
        loadWeaknesses()
    }

    private fun loadWeaknesses() {
        val cached = sessionManager.evaluationResult
        if (cached != null && cached.overallFeedback.weaknesses.isNotEmpty()) {
            val items = cached.overallFeedback.weaknesses.map { weak ->
                WeaknessAnalysisScreenUiState.WeaknessItem(
                    title = "Area for Improvement",
                    description = weak,
                    iconType = listOf("Speed", "VoiceOverOff", "PriorityHigh").random()
                )
            }
            _uiState.value = WeaknessAnalysisScreenUiState.Success(weaknesses = items)
        } else {
            _uiState.value = WeaknessAnalysisScreenUiState.Empty
        }
    }
}
