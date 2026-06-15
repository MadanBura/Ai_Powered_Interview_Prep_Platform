package com.interview.platform.ui.screens.mod10_interview_session.audio_review

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.interview.platform.domain.audio.AudioPlayer
import dagger.hilt.android.qualifiers.ApplicationContext
import android.content.Context
import java.io.File

@HiltViewModel
class AudioReviewScreenViewModel @Inject constructor(
    private val audioPlayer: AudioPlayer,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _uiState = MutableStateFlow<AudioReviewScreenUiState>(AudioReviewScreenUiState.Success())
    val uiState: StateFlow<AudioReviewScreenUiState> = _uiState.asStateFlow()

    private var timerJob: Job? = null
    private var totalDurationMs = 80000 // default fallback
    private var isPlayerPrepared = false
    private var inputFile: File? = null

    init {
        inputFile = File(context.cacheDir, "recording_temp.mp4")
    }

    fun togglePlayPause() {
        val currentState = _uiState.value as? AudioReviewScreenUiState.Success ?: return
        val isPlaying = !currentState.isPlaying

        _uiState.value = currentState.copy(isPlaying = isPlaying)

        if (isPlaying) {
            if (!isPlayerPrepared) {
                inputFile?.let { file ->
                    if (file.exists()) {
                        audioPlayer.playFile(file) {
                            // On complete
                            val state = _uiState.value as? AudioReviewScreenUiState.Success ?: return@playFile
                            _uiState.value = state.copy(progress = 1f, isPlaying = false)
                            isPlayerPrepared = false
                            timerJob?.cancel()
                        }
                        isPlayerPrepared = true
                        totalDurationMs = audioPlayer.getDuration()
                        if (totalDurationMs <= 0) totalDurationMs = 1000 // avoid div by 0
                        startPlayback()
                    }
                }
            } else {
                audioPlayer.resume()
                startPlayback()
            }
        } else {
            audioPlayer.pause()
            timerJob?.cancel()
        }
    }

    private fun startPlayback() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (true) {
                delay(100) // update faster for smooth UI
                
                if (!audioPlayer.isPlaying()) {
                    // Check if it naturally ended
                    continue
                }
                
                val currentPositionMs = audioPlayer.getCurrentPosition()
                val progress = currentPositionMs.toFloat() / totalDurationMs.toFloat()
                
                val displaySecs = currentPositionMs / 1000
                val m = displaySecs / 60
                val s = displaySecs % 60
                val timeStr = "$m:${s.toString().padStart(2, '0')}"

                val currentState = _uiState.value as? AudioReviewScreenUiState.Success ?: return@launch
                
                if (progress >= 1f) {
                    _uiState.value = currentState.copy(progress = 1f, currentTimeStr = timeStr, isPlaying = false)
                    break
                } else {
                    _uiState.value = currentState.copy(progress = progress, currentTimeStr = timeStr)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
        audioPlayer.stop()
    }

    fun submitAction(onSuccess: () -> Unit) {
        val currentState = _uiState.value as? AudioReviewScreenUiState.Success ?: return
        _uiState.value = currentState.copy(isLoading = true)
        // Simulate submit
        _uiState.value = currentState.copy(isLoading = false)
        onSuccess()
    }
}
