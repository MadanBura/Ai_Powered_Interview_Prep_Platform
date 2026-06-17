package com.interview.platform.domain.audio

import java.io.File

interface AudioPlayer {
    fun playFile(file: File, onCompletion: (() -> Unit)? = null)
    fun stop()
    fun pause()
    fun resume()
    fun getCurrentPosition(): Int
    fun getDuration(): Int
    fun isPlaying(): Boolean
}
