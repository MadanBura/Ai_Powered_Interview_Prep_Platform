package com.interview.platform.di.mod19_recommendation_engine

import com.interview.platform.data.remote.api.Mod19RecommendationEngineApiService
import com.interview.platform.data.repository.RecommendationRepositoryImpl
import com.interview.platform.domain.repository.RecommendationRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Mod19RecommendationEngineRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRecommendationRepository(
        impl: RecommendationRepositoryImpl
    ): RecommendationRepository
}

@Module
@InstallIn(SingletonComponent::class)
object Mod19RecommendationEngineApiModule {
    @Provides
    @Singleton
    fun provideMod19RecommendationEngineApiService(retrofit: Retrofit): Mod19RecommendationEngineApiService {
        return retrofit.create(Mod19RecommendationEngineApiService::class.java)
    }
}
