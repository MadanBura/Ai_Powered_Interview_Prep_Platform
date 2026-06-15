package com.interview.platform.di

import android.content.Context
import com.interview.platform.data.audio.AndroidAudioPlayer
import com.interview.platform.data.audio.AndroidAudioRecorder
import com.interview.platform.domain.audio.AudioPlayer
import com.interview.platform.domain.audio.AudioRecorder
import com.interview.platform.domain.audio.SpeechRecognizerService
import com.interview.platform.data.audio.AndroidSpeechRecognizer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AudioModule {

    @Provides
    @Singleton
    fun provideAudioRecorder(
        @ApplicationContext context: Context
    ): AudioRecorder {
        return AndroidAudioRecorder(context)
    }

    @Provides
    @Singleton
    fun provideAudioPlayer(
        @ApplicationContext context: Context
    ): AudioPlayer {
        return AndroidAudioPlayer(context)
    }

    @Provides
    @Singleton
    fun provideSpeechRecognizerService(
        @ApplicationContext context: Context
    ): SpeechRecognizerService {
        return AndroidSpeechRecognizer(context)
    }
}
