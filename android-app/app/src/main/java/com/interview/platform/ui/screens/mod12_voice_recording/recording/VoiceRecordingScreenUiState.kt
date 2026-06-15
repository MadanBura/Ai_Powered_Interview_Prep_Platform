package com.interview.platform.ui.screens.mod12_voice_recording.recording

sealed class VoiceRecordingScreenUiState {
    object Loading : VoiceRecordingScreenUiState()
    object Empty : VoiceRecordingScreenUiState()
    data class Success(
        val isRecording: Boolean = true,
        val elapsedSeconds: Int = 0,
        val elapsedTimeStr: String = "00:00",
        val waveHeights: List<Int> = listOf(15, 30, 45, 60, 80, 50, 70, 40, 20, 35)
    ) : VoiceRecordingScreenUiState()
    data class Error(val message: String) : VoiceRecordingScreenUiState()
}
