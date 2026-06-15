package com.interview.platform.data.repository;

import com.interview.platform.data.local.dao.DashboardDao;
import com.interview.platform.data.remote.api.Mod18DashboardApiService;
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
public final class DashboardRepositoryImpl_Factory implements Factory<DashboardRepositoryImpl> {
  private final Provider<Mod18DashboardApiService> apiServiceProvider;

  private final Provider<DashboardDao> daoProvider;

  public DashboardRepositoryImpl_Factory(Provider<Mod18DashboardApiService> apiServiceProvider,
      Provider<DashboardDao> daoProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.daoProvider = daoProvider;
  }

  @Override
  public DashboardRepositoryImpl get() {
    return newInstance(apiServiceProvider.get(), daoProvider.get());
  }

  public static DashboardRepositoryImpl_Factory create(
      Provider<Mod18DashboardApiService> apiServiceProvider, Provider<DashboardDao> daoProvider) {
    return new DashboardRepositoryImpl_Factory(apiServiceProvider, daoProvider);
  }

  public static DashboardRepositoryImpl newInstance(Mod18DashboardApiService apiService,
      DashboardDao dao) {
    return new DashboardRepositoryImpl(apiService, dao);
  }
}
