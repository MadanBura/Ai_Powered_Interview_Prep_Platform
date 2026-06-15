package com.interview.platform.data.repository;

import com.interview.platform.data.local.dao.ScoringEngineDao;
import com.interview.platform.data.remote.api.Mod15ScoringEngineApiService;
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
public final class ScoringEngineRepositoryImpl_Factory implements Factory<ScoringEngineRepositoryImpl> {
  private final Provider<Mod15ScoringEngineApiService> apiServiceProvider;

  private final Provider<ScoringEngineDao> daoProvider;

  public ScoringEngineRepositoryImpl_Factory(
      Provider<Mod15ScoringEngineApiService> apiServiceProvider,
      Provider<ScoringEngineDao> daoProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.daoProvider = daoProvider;
  }

  @Override
  public ScoringEngineRepositoryImpl get() {
    return newInstance(apiServiceProvider.get(), daoProvider.get());
  }

  public static ScoringEngineRepositoryImpl_Factory create(
      Provider<Mod15ScoringEngineApiService> apiServiceProvider,
      Provider<ScoringEngineDao> daoProvider) {
    return new ScoringEngineRepositoryImpl_Factory(apiServiceProvider, daoProvider);
  }

  public static ScoringEngineRepositoryImpl newInstance(Mod15ScoringEngineApiService apiService,
      ScoringEngineDao dao) {
    return new ScoringEngineRepositoryImpl(apiService, dao);
  }
}
