package com.interview.platform.di.mod14_ai_evaluation

import com.interview.platform.data.remote.api.Mod14AiEvaluationApiService
import com.interview.platform.data.repository.AiEvaluationRepositoryImpl
import com.interview.platform.domain.repository.AiEvaluationRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Mod14AiEvaluationRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAiEvaluationRepository(
        impl: AiEvaluationRepositoryImpl
    ): AiEvaluationRepository
}

@Module
@InstallIn(SingletonComponent::class)
object Mod14AiEvaluationApiModule {
    @Provides
    @Singleton
    fun provideMod14AiEvaluationApiService(retrofit: Retrofit): Mod14AiEvaluationApiService {
        return retrofit.create(Mod14AiEvaluationApiService::class.java)
    }
}
