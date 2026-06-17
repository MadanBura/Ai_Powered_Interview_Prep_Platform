package com.interview.platform.ui.screens.mod13_speech_to_text.speech_to_text

sealed class SpeechToTextScreenUiState {
    object Loading : SpeechToTextScreenUiState()
    object Empty : SpeechToTextScreenUiState()
    data class Success(
        val transcribedText: String = "",
        val isEditing: Boolean = false,
        val isRecording: Boolean = false,
        val isLoading: Boolean = false,
        val questionText: String = "",
        val currentStep: Int = 1,
        val totalSteps: Int = 5
    ) : SpeechToTextScreenUiState()
    data class Error(val message: String) : SpeechToTextScreenUiState()
}
