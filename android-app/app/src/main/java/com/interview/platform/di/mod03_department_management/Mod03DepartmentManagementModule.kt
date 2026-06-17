package com.interview.platform.di.mod03_department_management

import com.interview.platform.data.remote.api.Mod03DepartmentManagementApiService
import com.interview.platform.data.repository.DepartmentRepositoryImpl
import com.interview.platform.domain.repository.DepartmentRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Mod03DepartmentManagementRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindDepartmentRepository(
        impl: DepartmentRepositoryImpl
    ): DepartmentRepository
}

@Module
@InstallIn(SingletonComponent::class)
object Mod03DepartmentManagementApiModule {
    @Provides
    @Singleton
    fun provideMod03DepartmentManagementApiService(retrofit: Retrofit): Mod03DepartmentManagementApiService {
        return retrofit.create(Mod03DepartmentManagementApiService::class.java)
    }
}
