package com.interview.platform.data.repository;

import com.interview.platform.data.local.dao.ReportingDao;
import com.interview.platform.data.remote.api.Mod17ReportingApiService;
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
public final class ReportingRepositoryImpl_Factory implements Factory<ReportingRepositoryImpl> {
  private final Provider<Mod17ReportingApiService> apiServiceProvider;

  private final Provider<ReportingDao> daoProvider;

  public ReportingRepositoryImpl_Factory(Provider<Mod17ReportingApiService> apiServiceProvider,
      Provider<ReportingDao> daoProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.daoProvider = daoProvider;
  }

  @Override
  public ReportingRepositoryImpl get() {
    return newInstance(apiServiceProvider.get(), daoProvider.get());
  }

  public static ReportingRepositoryImpl_Factory create(
      Provider<Mod17ReportingApiService> apiServiceProvider, Provider<ReportingDao> daoProvider) {
    return new ReportingRepositoryImpl_Factory(apiServiceProvider, daoProvider);
  }

  public static ReportingRepositoryImpl newInstance(Mod17ReportingApiService apiService,
      ReportingDao dao) {
    return new ReportingRepositoryImpl(apiService, dao);
  }
}
