package com.interview.platform.di.mod20_analytics

import com.interview.platform.data.remote.api.Mod20AnalyticsApiService
import com.interview.platform.data.repository.AnalyticsRepositoryImpl
import com.interview.platform.domain.repository.AnalyticsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Mod20AnalyticsRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAnalyticsRepository(
        impl: AnalyticsRepositoryImpl
    ): AnalyticsRepository
}

@Module
@InstallIn(SingletonComponent::class)
object Mod20AnalyticsApiModule {
    @Provides
    @Singleton
    fun provideMod20AnalyticsApiService(retrofit: Retrofit): Mod20AnalyticsApiService {
        return retrofit.create(Mod20AnalyticsApiService::class.java)
    }
}
