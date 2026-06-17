package com.interview.platform.di.mod11_question_delivery

import com.interview.platform.data.remote.api.Mod11QuestionDeliveryApiService
import com.interview.platform.data.repository.QuestionDeliveryRepositoryImpl
import com.interview.platform.domain.repository.QuestionDeliveryRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Mod11QuestionDeliveryRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindQuestionDeliveryRepository(
        impl: QuestionDeliveryRepositoryImpl
    ): QuestionDeliveryRepository
}

@Module
@InstallIn(SingletonComponent::class)
object Mod11QuestionDeliveryApiModule {
    @Provides
    @Singleton
    fun provideMod11QuestionDeliveryApiService(retrofit: Retrofit): Mod11QuestionDeliveryApiService {
        return retrofit.create(Mod11QuestionDeliveryApiService::class.java)
    }
}
