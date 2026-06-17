package com.interview.platform.di.mod15_scoring_engine

import com.interview.platform.data.remote.api.Mod15ScoringEngineApiService
import com.interview.platform.data.repository.ScoringEngineRepositoryImpl
import com.interview.platform.domain.repository.ScoringEngineRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Mod15ScoringEngineRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindScoringEngineRepository(
        impl: ScoringEngineRepositoryImpl
    ): ScoringEngineRepository
}

@Module
@InstallIn(SingletonComponent::class)
object Mod15ScoringEngineApiModule {
    @Provides
    @Singleton
    fun provideMod15ScoringEngineApiService(retrofit: Retrofit): Mod15ScoringEngineApiService {
        return retrofit.create(Mod15ScoringEngineApiService::class.java)
    }
}
