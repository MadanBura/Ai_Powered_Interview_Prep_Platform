package com.interview.platform.domain.audio

import java.io.File

interface AudioRecorder {
    fun start(outputFile: File)
    fun stop()
    fun getMaxAmplitude(): Int
}
