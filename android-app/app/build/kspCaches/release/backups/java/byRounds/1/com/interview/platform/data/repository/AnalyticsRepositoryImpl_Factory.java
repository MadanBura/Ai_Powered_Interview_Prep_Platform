package com.interview.platform.data.repository;

import com.interview.platform.data.local.dao.AnalyticsDao;
import com.interview.platform.data.remote.api.Mod20AnalyticsApiService;
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
public final class AnalyticsRepositoryImpl_Factory implements Factory<AnalyticsRepositoryImpl> {
  private final Provider<Mod20AnalyticsApiService> apiServiceProvider;

  private final Provider<AnalyticsDao> daoProvider;

  public AnalyticsRepositoryImpl_Factory(Provider<Mod20AnalyticsApiService> apiServiceProvider,
      Provider<AnalyticsDao> daoProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.daoProvider = daoProvider;
  }

  @Override
  public AnalyticsRepositoryImpl get() {
    return newInstance(apiServiceProvider.get(), daoProvider.get());
  }

  public static AnalyticsRepositoryImpl_Factory create(
      Provider<Mod20AnalyticsApiService> apiServiceProvider, Provider<AnalyticsDao> daoProvider) {
    return new AnalyticsRepositoryImpl_Factory(apiServiceProvider, daoProvider);
  }

  public static AnalyticsRepositoryImpl newInstance(Mod20AnalyticsApiService apiService,
      AnalyticsDao dao) {
    return new AnalyticsRepositoryImpl(apiService, dao);
  }
}
