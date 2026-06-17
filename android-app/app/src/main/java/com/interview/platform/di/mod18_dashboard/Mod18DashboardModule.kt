package com.interview.platform.di.mod18_dashboard

import com.interview.platform.data.remote.api.Mod18DashboardApiService
import com.interview.platform.data.repository.DashboardRepositoryImpl
import com.interview.platform.domain.repository.DashboardRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Mod18DashboardRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindDashboardRepository(
        impl: DashboardRepositoryImpl
    ): DashboardRepository
}

@Module
@InstallIn(SingletonComponent::class)
object Mod18DashboardApiModule {
    @Provides
    @Singleton
    fun provideMod18DashboardApiService(retrofit: Retrofit): Mod18DashboardApiService {
        return retrofit.create(Mod18DashboardApiService::class.java)
    }
}
