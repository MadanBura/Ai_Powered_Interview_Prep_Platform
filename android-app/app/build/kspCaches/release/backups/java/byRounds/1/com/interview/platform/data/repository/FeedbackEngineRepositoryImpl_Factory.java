package com.interview.platform.data.repository;

import com.interview.platform.data.local.dao.FeedbackEngineDao;
import com.interview.platform.data.remote.api.Mod16FeedbackEngineApiService;
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
public final class FeedbackEngineRepositoryImpl_Factory implements Factory<FeedbackEngineRepositoryImpl> {
  private final Provider<Mod16FeedbackEngineApiService> apiServiceProvider;

  private final Provider<FeedbackEngineDao> daoProvider;

  public FeedbackEngineRepositoryImpl_Factory(
      Provider<Mod16FeedbackEngineApiService> apiServiceProvider,
      Provider<FeedbackEngineDao> daoProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.daoProvider = daoProvider;
  }

  @Override
  public FeedbackEngineRepositoryImpl get() {
    return newInstance(apiServiceProvider.get(), daoProvider.get());
  }

  public static FeedbackEngineRepositoryImpl_Factory create(
      Provider<Mod16FeedbackEngineApiService> apiServiceProvider,
      Provider<FeedbackEngineDao> daoProvider) {
    return new FeedbackEngineRepositoryImpl_Factory(apiServiceProvider, daoProvider);
  }

  public static FeedbackEngineRepositoryImpl newInstance(Mod16FeedbackEngineApiService apiService,
      FeedbackEngineDao dao) {
    return new FeedbackEngineRepositoryImpl(apiService, dao);
  }
}
