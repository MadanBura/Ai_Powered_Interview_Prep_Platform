package com.interview.platform.data.repository;

import com.interview.platform.data.local.dao.AiEvaluationDao;
import com.interview.platform.data.remote.api.Mod14AiEvaluationApiService;
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
public final class AiEvaluationRepositoryImpl_Factory implements Factory<AiEvaluationRepositoryImpl> {
  private final Provider<Mod14AiEvaluationApiService> apiServiceProvider;

  private final Provider<AiEvaluationDao> daoProvider;

  public AiEvaluationRepositoryImpl_Factory(
      Provider<Mod14AiEvaluationApiService> apiServiceProvider,
      Provider<AiEvaluationDao> daoProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.daoProvider = daoProvider;
  }

  @Override
  public AiEvaluationRepositoryImpl get() {
    return newInstance(apiServiceProvider.get(), daoProvider.get());
  }

  public static AiEvaluationRepositoryImpl_Factory create(
      Provider<Mod14AiEvaluationApiService> apiServiceProvider,
      Provider<AiEvaluationDao> daoProvider) {
    return new AiEvaluationRepositoryImpl_Factory(apiServiceProvider, daoProvider);
  }

  public static AiEvaluationRepositoryImpl newInstance(Mod14AiEvaluationApiService apiService,
      AiEvaluationDao dao) {
    return new AiEvaluationRepositoryImpl(apiService, dao);
  }
}
