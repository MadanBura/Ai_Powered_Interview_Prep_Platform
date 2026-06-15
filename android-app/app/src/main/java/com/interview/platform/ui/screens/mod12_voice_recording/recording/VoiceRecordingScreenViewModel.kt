package com.interview.platform.ui.screens.mod12_voice_recording.recording

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
import kotlin.random.Random
import com.interview.platform.domain.audio.AudioRecorder
import dagger.hilt.android.qualifiers.ApplicationContext
import android.content.Context
import java.io.File

@HiltViewModel
class VoiceRecordingScreenViewModel @Inject constructor(
    private val audioRecorder: AudioRecorder,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _uiState = MutableStateFlow<VoiceRecordingScreenUiState>(VoiceRecordingScreenUiState.Success())
    val uiState: StateFlow<VoiceRecordingScreenUiState> = _uiState.asStateFlow()
    
    private var recordingJob: Job? = null
    private var outputFile: File? = null
    
    init {
        outputFile = File(context.cacheDir, "recording_temp.mp4")
    }

    fun startRecording() {
        if (recordingJob?.isActive == true) return
        audioRecorder.start(outputFile!!)
        startRecordingTimer()
    }

    private fun startRecordingTimer() {
        recordingJob = viewModelScope.launch {
            var seconds = 0
            while (true) {
                delay(1000)
                seconds++
                val m = seconds / 60
                val s = seconds % 60
                val timeStr = "${m.toString().padStart(2, '0')}:${s.toString().padStart(2, '0')}"
                
                // Use real amplitude mapped roughly to wave heights
                val maxAmp = audioRecorder.getMaxAmplitude()
                // Map amplitude (0-32767) to a visually reasonable height range (15-85)
                val baseHeight = 15
                val scaledHeight = (maxAmp.toFloat() / 32767f * 70).toInt() + baseHeight
                val constrainedHeight = scaledHeight.coerceIn(15, 85)
                
                // Shift previous waves left and add new wave at the end
                val currentState = _uiState.value as? VoiceRecordingScreenUiState.Success ?: continue
                val newWaves = currentState.waveHeights.drop(1) + constrainedHeight
                
                _uiState.value = currentState.copy(
                    elapsedSeconds = seconds,
                    elapsedTimeStr = timeStr,
                    waveHeights = newWaves
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        recordingJob?.cancel()
        audioRecorder.stop()
    }

    fun submitAction(onSuccess: () -> Unit) {
        recordingJob?.cancel()
        audioRecorder.stop()
        onSuccess()
    }
}
