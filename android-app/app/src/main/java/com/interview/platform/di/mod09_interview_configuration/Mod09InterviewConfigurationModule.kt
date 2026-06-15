package com.interview.platform.di.mod09_interview_configuration

import com.interview.platform.data.remote.api.Mod09InterviewConfigurationApiService
import com.interview.platform.data.repository.InterviewConfigRepositoryImpl
import com.interview.platform.domain.repository.InterviewConfigRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Mod09InterviewConfigurationRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindInterviewConfigRepository(
        impl: InterviewConfigRepositoryImpl
    ): InterviewConfigRepository
}

@Module
@InstallIn(SingletonComponent::class)
object Mod09InterviewConfigurationApiModule {
    @Provides
    @Singleton
    fun provideMod09InterviewConfigurationApiService(retrofit: Retrofit): Mod09InterviewConfigurationApiService {
        return retrofit.create(Mod09InterviewConfigurationApiService::class.java)
    }
}
