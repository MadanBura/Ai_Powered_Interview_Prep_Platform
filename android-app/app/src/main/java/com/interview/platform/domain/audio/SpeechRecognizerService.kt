package com.interview.platform.domain.audio

interface SpeechRecognizerService {
    fun startListening(
        onResult: (String) -> Unit,
        onPartialResult: (String) -> Unit,
        onError: (String) -> Unit
    )
    fun stopListening()
}
