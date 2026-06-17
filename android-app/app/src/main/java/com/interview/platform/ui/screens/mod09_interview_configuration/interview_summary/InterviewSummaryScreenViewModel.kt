package com.interview.platform.ui.screens.mod09_interview_configuration.interview_summary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.platform.data.remote.api.Mod10InterviewSessionApiService
import com.interview.platform.data.remote.dto.mod10_interview_session.InterviewSessionRequestDto
import com.interview.platform.data.repository.InterviewSessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InterviewSummaryScreenViewModel @Inject constructor(
    private val sessionApi: Mod10InterviewSessionApiService,
    private val sessionManager: InterviewSessionManager
) : ViewModel() {
    private val _uiState = MutableStateFlow<InterviewSummaryScreenUiState>(
        InterviewSummaryScreenUiState.Success(
            technology = sessionManager.selectedTechnologyName ?: "Technology Not Selected",
            experienceLevel = sessionManager.selectedExperienceLevelName ?: "Level Not Selected",
            questionCount = sessionManager.questionCount
        )
    )
    val uiState: StateFlow<InterviewSummaryScreenUiState> = _uiState.asStateFlow()
    
    fun submitAction(onSuccess: () -> Unit) {
        val currentState = _uiState.value as? InterviewSummaryScreenUiState.Success
        _uiState.value = currentState?.copy(isLoading = true) ?: InterviewSummaryScreenUiState.Success(isLoading = true)
        viewModelScope.launch {
            try {
                val request = InterviewSessionRequestDto(
                    departmentId = sessionManager.selectedDepartmentId,
                    technologyIds = sessionManager.selectedTechnologyIds,
                    experienceLevelId = sessionManager.selectedExperienceLevelId,
                    technologyName = sessionManager.selectedTechnologyName
                )
                val response = sessionApi.initializeSession(request)
                if (response.isSuccessful) {
                    response.body()?.data?.id?.let {
                        sessionManager.currentSessionId = it
                    }
                    _uiState.value = InterviewSummaryScreenUiState.Success(isLoading = false)
                    onSuccess()
                } else {
                    _uiState.value = InterviewSummaryScreenUiState.Error("Failed to initialize session: ${response.code()}")
                }
            } catch (e: Exception) {
                _uiState.value = InterviewSummaryScreenUiState.Error("Network error: ${e.message}")
            }
        }
    }
}
