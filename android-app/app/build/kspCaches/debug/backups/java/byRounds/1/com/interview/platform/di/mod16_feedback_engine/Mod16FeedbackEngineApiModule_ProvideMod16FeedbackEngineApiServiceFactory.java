package com.interview.platform.di.mod16_feedback_engine;

import com.interview.platform.data.remote.api.Mod16FeedbackEngineApiService;
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
public final class Mod16FeedbackEngineApiModule_ProvideMod16FeedbackEngineApiServiceFactory implements Factory<Mod16FeedbackEngineApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public Mod16FeedbackEngineApiModule_ProvideMod16FeedbackEngineApiServiceFactory(
      Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public Mod16FeedbackEngineApiService get() {
    return provideMod16FeedbackEngineApiService(retrofitProvider.get());
  }

  public static Mod16FeedbackEngineApiModule_ProvideMod16FeedbackEngineApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new Mod16FeedbackEngineApiModule_ProvideMod16FeedbackEngineApiServiceFactory(retrofitProvider);
  }

  public static Mod16FeedbackEngineApiService provideMod16FeedbackEngineApiService(
      Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(Mod16FeedbackEngineApiModule.INSTANCE.provideMod16FeedbackEngineApiService(retrofit));
  }
}
