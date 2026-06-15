package com.interview.platform.data.repository;

import com.interview.platform.data.local.dao.InterviewConfigDao;
import com.interview.platform.data.remote.api.Mod09InterviewConfigurationApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class InterviewConfigRepositoryImpl_Factory implements Factory<InterviewConfigRepositoryImpl> {
  private final Provider<Mod09InterviewConfigurationApiService> apiServiceProvider;

  private final Provider<InterviewConfigDao> daoProvider;

  public InterviewConfigRepositoryImpl_Factory(
      Provider<Mod09InterviewConfigurationApiService> apiServiceProvider,
      Provider<InterviewConfigDao> daoProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.daoProvider = daoProvider;
  }

  @Override
  public InterviewConfigRepositoryImpl get() {
    return newInstance(apiServiceProvider.get(), daoProvider.get());
  }

  public static InterviewConfigRepositoryImpl_Factory create(
      Provider<Mod09InterviewConfigurationApiService> apiServiceProvider,
      Provider<InterviewConfigDao> daoProvider) {
    return new InterviewConfigRepositoryImpl_Factory(apiServiceProvider, daoProvider);
  }

  public static InterviewConfigRepositoryImpl newInstance(
      Mod09InterviewConfigurationApiService apiService, InterviewConfigDao dao) {
    return new InterviewConfigRepositoryImpl(apiService, dao);
  }
}
