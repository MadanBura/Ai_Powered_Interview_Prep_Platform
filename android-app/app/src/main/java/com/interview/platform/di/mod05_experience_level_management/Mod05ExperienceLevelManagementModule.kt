package com.interview.platform.di.mod05_experience_level_management

import com.interview.platform.data.remote.api.Mod05ExperienceLevelManagementApiService
import com.interview.platform.data.repository.ExperienceLevelRepositoryImpl
import com.interview.platform.domain.repository.ExperienceLevelRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Mod05ExperienceLevelManagementRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindExperienceLevelRepository(
        impl: ExperienceLevelRepositoryImpl
    ): ExperienceLevelRepository
}

@Module
@InstallIn(SingletonComponent::class)
object Mod05ExperienceLevelManagementApiModule {
    @Provides
    @Singleton
    fun provideMod05ExperienceLevelManagementApiService(retrofit: Retrofit): Mod05ExperienceLevelManagementApiService {
        return retrofit.create(Mod05ExperienceLevelManagementApiService::class.java)
    }
}
