package com.interview.platform.di.mod13_speech_to_text

import com.interview.platform.data.remote.api.Mod13SpeechToTextApiService
import com.interview.platform.data.repository.SpeechToTextRepositoryImpl
import com.interview.platform.domain.repository.SpeechToTextRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Mod13SpeechToTextRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindSpeechToTextRepository(
        impl: SpeechToTextRepositoryImpl
    ): SpeechToTextRepository
}

@Module
@InstallIn(SingletonComponent::class)
object Mod13SpeechToTextApiModule {
    @Provides
    @Singleton
    fun provideMod13SpeechToTextApiService(retrofit: Retrofit): Mod13SpeechToTextApiService {
        return retrofit.create(Mod13SpeechToTextApiService::class.java)
    }
}
