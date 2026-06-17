package com.interview.platform.di.mod02_user_profile

import com.interview.platform.data.remote.api.Mod02UserProfileApiService
import com.interview.platform.data.repository.ProfileRepositoryImpl
import com.interview.platform.domain.repository.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Mod02UserProfileRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindProfileRepository(
        impl: ProfileRepositoryImpl
    ): ProfileRepository
}

@Module
@InstallIn(SingletonComponent::class)
object Mod02UserProfileApiModule {
    @Provides
    @Singleton
    fun provideMod02UserProfileApiService(retrofit: Retrofit): Mod02UserProfileApiService {
        return retrofit.create(Mod02UserProfileApiService::class.java)
    }
}
