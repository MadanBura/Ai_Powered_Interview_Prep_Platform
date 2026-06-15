package com.interview.platform.data.repository;

import com.interview.platform.data.remote.api.Mod19RecommendationEngineApiService;
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
public final class RecommendationRepositoryImpl_Factory implements Factory<RecommendationRepositoryImpl> {
  private final Provider<Mod19RecommendationEngineApiService> apiServiceProvider;

  public RecommendationRepositoryImpl_Factory(
      Provider<Mod19RecommendationEngineApiService> apiServiceProvider) {
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public RecommendationRepositoryImpl get() {
    return newInstance(apiServiceProvider.get());
  }

  public static RecommendationRepositoryImpl_Factory create(
      Provider<Mod19RecommendationEngineApiService> apiServiceProvider) {
    return new RecommendationRepositoryImpl_Factory(apiServiceProvider);
  }

  public static RecommendationRepositoryImpl newInstance(
      Mod19RecommendationEngineApiService apiService) {
    return new RecommendationRepositoryImpl(apiService);
  }
}
