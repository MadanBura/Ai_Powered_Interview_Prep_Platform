package com.interview.platform.di.mod15_scoring_engine;

import com.interview.platform.data.remote.api.Mod15ScoringEngineApiService;
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
public final class Mod15ScoringEngineApiModule_ProvideMod15ScoringEngineApiServiceFactory implements Factory<Mod15ScoringEngineApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public Mod15ScoringEngineApiModule_ProvideMod15ScoringEngineApiServiceFactory(
      Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public Mod15ScoringEngineApiService get() {
    return provideMod15ScoringEngineApiService(retrofitProvider.get());
  }

  public static Mod15ScoringEngineApiModule_ProvideMod15ScoringEngineApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new Mod15ScoringEngineApiModule_ProvideMod15ScoringEngineApiServiceFactory(retrofitProvider);
  }

  public static Mod15ScoringEngineApiService provideMod15ScoringEngineApiService(
      Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(Mod15ScoringEngineApiModule.INSTANCE.provideMod15ScoringEngineApiService(retrofit));
  }
}
