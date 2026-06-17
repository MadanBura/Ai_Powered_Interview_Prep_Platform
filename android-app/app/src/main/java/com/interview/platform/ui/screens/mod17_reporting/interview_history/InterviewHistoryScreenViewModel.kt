package com.interview.platform.ui.screens.mod17_reporting.interview_history

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

import androidx.lifecycle.viewModelScope
import com.interview.platform.data.remote.api.Mod10InterviewSessionApiService
import kotlinx.coroutines.launch

@HiltViewModel
class InterviewHistoryScreenViewModel @Inject constructor(
    private val sessionApi: Mod10InterviewSessionApiService
) : ViewModel() {
    private val _uiState = MutableStateFlow<InterviewHistoryScreenUiState>(InterviewHistoryScreenUiState.Loading)
    val uiState: StateFlow<InterviewHistoryScreenUiState> = _uiState.asStateFlow()
    
    init {
        fetchHistory()
    }

    fun fetchHistory() {
        viewModelScope.launch {
            _uiState.value = InterviewHistoryScreenUiState.Loading
            try {
                val response = sessionApi.getInterviewHistory()
                if (response.isSuccessful) {
                    val data = response.body()?.data
                    if (data.isNullOrEmpty()) {
                        _uiState.value = InterviewHistoryScreenUiState.Empty
                    } else {
                        // Create a Success state with the DTOs
                        _uiState.value = InterviewHistoryScreenUiState.Success(data)
                    }
                } else {
                    _uiState.value = InterviewHistoryScreenUiState.Error("Failed to fetch history")
                }
            } catch (e: Exception) {
                _uiState.value = InterviewHistoryScreenUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
