package com.interview.platform.di.mod10_interview_session

import com.interview.platform.data.remote.api.Mod10InterviewSessionApiService
import com.interview.platform.data.repository.InterviewSessionRepositoryImpl
import com.interview.platform.domain.repository.InterviewSessionRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Mod10InterviewSessionRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindInterviewSessionRepository(
        impl: InterviewSessionRepositoryImpl
    ): InterviewSessionRepository
}

@Module
@InstallIn(SingletonComponent::class)
object Mod10InterviewSessionApiModule {
    @Provides
    @Singleton
    fun provideMod10InterviewSessionApiService(retrofit: Retrofit): Mod10InterviewSessionApiService {
        return retrofit.create(Mod10InterviewSessionApiService::class.java)
    }
}
