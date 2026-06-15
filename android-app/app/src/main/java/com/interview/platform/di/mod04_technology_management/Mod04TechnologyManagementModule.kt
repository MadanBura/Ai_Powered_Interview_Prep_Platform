package com.interview.platform.di.mod04_technology_management

import com.interview.platform.data.remote.api.Mod04TechnologyManagementApiService
import com.interview.platform.data.repository.TechnologyRepositoryImpl
import com.interview.platform.domain.repository.TechnologyRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Mod04TechnologyManagementRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindTechnologyRepository(
        impl: TechnologyRepositoryImpl
    ): TechnologyRepository
}

@Module
@InstallIn(SingletonComponent::class)
object Mod04TechnologyManagementApiModule {
    @Provides
    @Singleton
    fun provideMod04TechnologyManagementApiService(retrofit: Retrofit): Mod04TechnologyManagementApiService {
        return retrofit.create(Mod04TechnologyManagementApiService::class.java)
    }
}
