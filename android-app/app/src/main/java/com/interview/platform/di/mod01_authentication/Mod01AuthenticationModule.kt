package com.interview.platform.di.mod01_authentication

import com.interview.platform.data.remote.api.Mod01AuthenticationApiService
import com.interview.platform.data.repository.AuthDataRepositoryImpl
import com.interview.platform.domain.repository.AuthDataRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Mod01AuthenticationRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAuthDataRepository(
        impl: AuthDataRepositoryImpl
    ): AuthDataRepository
}

@Module
@InstallIn(SingletonComponent::class)
object Mod01AuthenticationApiModule {
    @Provides
    @Singleton
    fun provideMod01AuthenticationApiService(retrofit: Retrofit): Mod01AuthenticationApiService {
        return retrofit.create(Mod01AuthenticationApiService::class.java)
    }
}
