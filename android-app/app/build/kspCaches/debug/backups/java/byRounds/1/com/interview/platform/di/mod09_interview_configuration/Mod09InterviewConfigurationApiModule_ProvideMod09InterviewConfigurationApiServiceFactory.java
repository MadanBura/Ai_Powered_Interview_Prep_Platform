package com.interview.platform.di.mod09_interview_configuration;

import com.interview.platform.data.remote.api.Mod09InterviewConfigurationApiService;
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
public final class Mod09InterviewConfigurationApiModule_ProvideMod09InterviewConfigurationApiServiceFactory implements Factory<Mod09InterviewConfigurationApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public Mod09InterviewConfigurationApiModule_ProvideMod09InterviewConfigurationApiServiceFactory(
      Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public Mod09InterviewConfigurationApiService get() {
    return provideMod09InterviewConfigurationApiService(retrofitProvider.get());
  }

  public static Mod09InterviewConfigurationApiModule_ProvideMod09InterviewConfigurationApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new Mod09InterviewConfigurationApiModule_ProvideMod09InterviewConfigurationApiServiceFactory(retrofitProvider);
  }

  public static Mod09InterviewConfigurationApiService provideMod09InterviewConfigurationApiService(
      Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(Mod09InterviewConfigurationApiModule.INSTANCE.provideMod09InterviewConfigurationApiService(retrofit));
  }
}
