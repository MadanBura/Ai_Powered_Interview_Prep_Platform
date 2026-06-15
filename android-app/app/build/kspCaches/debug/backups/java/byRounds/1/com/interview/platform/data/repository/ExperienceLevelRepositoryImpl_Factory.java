package com.interview.platform.data.repository;

import com.interview.platform.data.local.dao.ExperienceLevelDao;
import com.interview.platform.data.remote.api.Mod05ExperienceLevelManagementApiService;
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
public final class ExperienceLevelRepositoryImpl_Factory implements Factory<ExperienceLevelRepositoryImpl> {
  private final Provider<Mod05ExperienceLevelManagementApiService> apiServiceProvider;

  private final Provider<ExperienceLevelDao> daoProvider;

  public ExperienceLevelRepositoryImpl_Factory(
      Provider<Mod05ExperienceLevelManagementApiService> apiServiceProvider,
      Provider<ExperienceLevelDao> daoProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.daoProvider = daoProvider;
  }

  @Override
  public ExperienceLevelRepositoryImpl get() {
    return newInstance(apiServiceProvider.get(), daoProvider.get());
  }

  public static ExperienceLevelRepositoryImpl_Factory create(
      Provider<Mod05ExperienceLevelManagementApiService> apiServiceProvider,
      Provider<ExperienceLevelDao> daoProvider) {
    return new ExperienceLevelRepositoryImpl_Factory(apiServiceProvider, daoProvider);
  }

  public static ExperienceLevelRepositoryImpl newInstance(
      Mod05ExperienceLevelManagementApiService apiService, ExperienceLevelDao dao) {
    return new ExperienceLevelRepositoryImpl(apiService, dao);
  }
}
