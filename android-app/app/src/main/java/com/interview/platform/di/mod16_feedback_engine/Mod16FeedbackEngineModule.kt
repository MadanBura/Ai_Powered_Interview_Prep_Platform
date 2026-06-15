package com.interview.platform.di.mod16_feedback_engine

import com.interview.platform.data.remote.api.Mod16FeedbackEngineApiService
import com.interview.platform.data.repository.FeedbackEngineRepositoryImpl
import com.interview.platform.domain.repository.FeedbackEngineRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Mod16FeedbackEngineRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindFeedbackEngineRepository(
        impl: FeedbackEngineRepositoryImpl
    ): FeedbackEngineRepository
}

@Module
@InstallIn(SingletonComponent::class)
object Mod16FeedbackEngineApiModule {
    @Provides
    @Singleton
    fun provideMod16FeedbackEngineApiService(retrofit: Retrofit): Mod16FeedbackEngineApiService {
        return retrofit.create(Mod16FeedbackEngineApiService::class.java)
    }
}
