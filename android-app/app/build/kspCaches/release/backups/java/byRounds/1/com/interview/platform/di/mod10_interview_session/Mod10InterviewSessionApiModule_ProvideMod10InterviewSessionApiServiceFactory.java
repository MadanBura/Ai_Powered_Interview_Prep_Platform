package com.interview.platform.di.mod10_interview_session;

import com.interview.platform.data.remote.api.Mod10InterviewSessionApiService;
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
public final class Mod10InterviewSessionApiModule_ProvideMod10InterviewSessionApiServiceFactory implements Factory<Mod10InterviewSessionApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public Mod10InterviewSessionApiModule_ProvideMod10InterviewSessionApiServiceFactory(
      Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public Mod10InterviewSessionApiService get() {
    return provideMod10InterviewSessionApiService(retrofitProvider.get());
  }

  public static Mod10InterviewSessionApiModule_ProvideMod10InterviewSessionApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new Mod10InterviewSessionApiModule_ProvideMod10InterviewSessionApiServiceFactory(retrofitProvider);
  }

  public static Mod10InterviewSessionApiService provideMod10InterviewSessionApiService(
      Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(Mod10InterviewSessionApiModule.INSTANCE.provideMod10InterviewSessionApiService(retrofit));
  }
}
