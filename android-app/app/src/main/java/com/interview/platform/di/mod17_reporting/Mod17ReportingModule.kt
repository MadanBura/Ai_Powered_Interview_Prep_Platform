package com.interview.platform.di.mod17_reporting

import com.interview.platform.data.remote.api.Mod17ReportingApiService
import com.interview.platform.data.repository.ReportingRepositoryImpl
import com.interview.platform.domain.repository.ReportingRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Mod17ReportingRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindReportingRepository(
        impl: ReportingRepositoryImpl
    ): ReportingRepository
}

@Module
@InstallIn(SingletonComponent::class)
object Mod17ReportingApiModule {
    @Provides
    @Singleton
    fun provideMod17ReportingApiService(retrofit: Retrofit): Mod17ReportingApiService {
        return retrofit.create(Mod17ReportingApiService::class.java)
    }
}
