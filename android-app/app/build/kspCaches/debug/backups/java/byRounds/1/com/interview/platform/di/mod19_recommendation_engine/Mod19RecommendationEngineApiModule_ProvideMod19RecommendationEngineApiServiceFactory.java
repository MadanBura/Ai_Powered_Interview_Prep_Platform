package com.interview.platform.di.mod19_recommendation_engine;

import com.interview.platform.data.remote.api.Mod19RecommendationEngineApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

@ScopeMetadata("javax.inject.Singleton")
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
public final class Mod19RecommendationEngineApiModule_ProvideMod19RecommendationEngineApiServiceFactory implements Factory<Mod19RecommendationEngineApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public Mod19RecommendationEngineApiModule_ProvideMod19RecommendationEngineApiServiceFactory(
      Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public Mod19RecommendationEngineApiService get() {
    return provideMod19RecommendationEngineApiService(retrofitProvider.get());
  }

  public static Mod19RecommendationEngineApiModule_ProvideMod19RecommendationEngineApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new Mod19RecommendationEngineApiModule_ProvideMod19RecommendationEngineApiServiceFactory(retrofitProvider);
  }

  public static Mod19RecommendationEngineApiService provideMod19RecommendationEngineApiService(
      Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(Mod19RecommendationEngineApiModule.INSTANCE.provideMod19RecommendationEngineApiService(retrofit));
  }
}
