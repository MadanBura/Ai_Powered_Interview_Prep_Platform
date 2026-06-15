package com.interview.platform.di.mod12_voice_recording

import com.interview.platform.data.remote.api.Mod12VoiceRecordingApiService
import com.interview.platform.data.repository.VoiceRecordingRepositoryImpl
import com.interview.platform.domain.repository.VoiceRecordingRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Mod12VoiceRecordingRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindVoiceRecordingRepository(
        impl: VoiceRecordingRepositoryImpl
    ): VoiceRecordingRepository
}

@Module
@InstallIn(SingletonComponent::class)
object Mod12VoiceRecordingApiModule {
    @Provides
    @Singleton
    fun provideMod12VoiceRecordingApiService(retrofit: Retrofit): Mod12VoiceRecordingApiService {
        return retrofit.create(Mod12VoiceRecordingApiService::class.java)
    }
}
