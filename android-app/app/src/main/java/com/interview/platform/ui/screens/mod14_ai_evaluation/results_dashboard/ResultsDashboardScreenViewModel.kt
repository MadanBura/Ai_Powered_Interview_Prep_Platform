package com.interview.platform.ui.screens.mod14_ai_evaluation.results_dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.platform.data.remote.api.Mod14AiEvaluationApiService
import com.interview.platform.data.remote.dto.mod14_ai_evaluation.toDomain
import com.interview.platform.data.repository.InterviewSessionManager
import com.interview.platform.domain.model.mod14_ai_evaluation.MockEvaluationProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultsDashboardScreenViewModel @Inject constructor(
    private val evaluationApi: Mod14AiEvaluationApiService,
    private val sessionManager: InterviewSessionManager
) : ViewModel() {
    private val _uiState = MutableStateFlow<ResultsDashboardScreenUiState>(ResultsDashboardScreenUiState.Loading)
    val uiState: StateFlow<ResultsDashboardScreenUiState> = _uiState.asStateFlow()
    
    init {
        loadResults()
    }

    private fun loadResults() {
        val cached = sessionManager.evaluationResult
        if (cached != null) {
            _uiState.value = ResultsDashboardScreenUiState.Success(result = cached)
            return
        }

        val sessionId = sessionManager.currentSessionId
        if (sessionId == null) {
            _uiState.value = ResultsDashboardScreenUiState.Error("No active session found")
            return
        }

        viewModelScope.launch {
            try {
                val response = evaluationApi.retrieveEvaluation(sessionId)
                if (response.isSuccessful && response.body()?.success == true) {
                    val evalData = response.body()?.data
                    if (evalData != null) {
                        val result = evalData.toDomain()
                        sessionManager.evaluationResult = result
                        _uiState.value = ResultsDashboardScreenUiState.Success(result = result)
                    } else {
                        _uiState.value = ResultsDashboardScreenUiState.Error("Backend returned null evaluation data")
                    }
                } else {
                    _uiState.value = ResultsDashboardScreenUiState.Error("Failed to load results")
                }
            } catch (e: Exception) {
                _uiState.value = ResultsDashboardScreenUiState.Error("Network error: ${e.message}")
            }
        }
    }
}
