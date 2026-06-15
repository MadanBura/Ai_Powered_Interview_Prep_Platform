package com.interview.platform.data.repository;

import com.interview.platform.data.local.dao.TechnologyDao;
import com.interview.platform.data.remote.api.Mod04TechnologyManagementApiService;
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
public final class TechnologyRepositoryImpl_Factory implements Factory<TechnologyRepositoryImpl> {
  private final Provider<Mod04TechnologyManagementApiService> apiServiceProvider;

  private final Provider<TechnologyDao> daoProvider;

  public TechnologyRepositoryImpl_Factory(
      Provider<Mod04TechnologyManagementApiService> apiServiceProvider,
      Provider<TechnologyDao> daoProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.daoProvider = daoProvider;
  }

  @Override
  public TechnologyRepositoryImpl get() {
    return newInstance(apiServiceProvider.get(), daoProvider.get());
  }

  public static TechnologyRepositoryImpl_Factory create(
      Provider<Mod04TechnologyManagementApiService> apiServiceProvider,
      Provider<TechnologyDao> daoProvider) {
    return new TechnologyRepositoryImpl_Factory(apiServiceProvider, daoProvider);
  }

  public static TechnologyRepositoryImpl newInstance(Mod04TechnologyManagementApiService apiService,
      TechnologyDao dao) {
    return new TechnologyRepositoryImpl(apiService, dao);
  }
}
