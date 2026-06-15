package com.interview.platform.di.mod14_ai_evaluation;

import com.interview.platform.data.remote.api.Mod14AiEvaluationApiService;
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
public final class Mod14AiEvaluationApiModule_ProvideMod14AiEvaluationApiServiceFactory implements Factory<Mod14AiEvaluationApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public Mod14AiEvaluationApiModule_ProvideMod14AiEvaluationApiServiceFactory(
      Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public Mod14AiEvaluationApiService get() {
    return provideMod14AiEvaluationApiService(retrofitProvider.get());
  }

  public static Mod14AiEvaluationApiModule_ProvideMod14AiEvaluationApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new Mod14AiEvaluationApiModule_ProvideMod14AiEvaluationApiServiceFactory(retrofitProvider);
  }

  public static Mod14AiEvaluationApiService provideMod14AiEvaluationApiService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(Mod14AiEvaluationApiModule.INSTANCE.provideMod14AiEvaluationApiService(retrofit));
  }
}
