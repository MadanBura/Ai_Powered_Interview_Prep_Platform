package com.interview.platform.data.repository;

import com.interview.platform.data.local.dao.InterviewSessionDao;
import com.interview.platform.data.remote.api.Mod10InterviewSessionApiService;
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
public final class InterviewSessionRepositoryImpl_Factory implements Factory<InterviewSessionRepositoryImpl> {
  private final Provider<Mod10InterviewSessionApiService> apiServiceProvider;

  private final Provider<InterviewSessionDao> daoProvider;

  public InterviewSessionRepositoryImpl_Factory(
      Provider<Mod10InterviewSessionApiService> apiServiceProvider,
      Provider<InterviewSessionDao> daoProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.daoProvider = daoProvider;
  }

  @Override
  public InterviewSessionRepositoryImpl get() {
    return newInstance(apiServiceProvider.get(), daoProvider.get());
  }

  public static InterviewSessionRepositoryImpl_Factory create(
      Provider<Mod10InterviewSessionApiService> apiServiceProvider,
      Provider<InterviewSessionDao> daoProvider) {
    return new InterviewSessionRepositoryImpl_Factory(apiServiceProvider, daoProvider);
  }

  public static InterviewSessionRepositoryImpl newInstance(
      Mod10InterviewSessionApiService apiService, InterviewSessionDao dao) {
    return new InterviewSessionRepositoryImpl(apiService, dao);
  }
}
