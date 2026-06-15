package com.interview.platform.di.mod20_analytics;

import com.interview.platform.data.remote.api.Mod20AnalyticsApiService;
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
public final class Mod20AnalyticsApiModule_ProvideMod20AnalyticsApiServiceFactory implements Factory<Mod20AnalyticsApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public Mod20AnalyticsApiModule_ProvideMod20AnalyticsApiServiceFactory(
      Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public Mod20AnalyticsApiService get() {
    return provideMod20AnalyticsApiService(retrofitProvider.get());
  }

  public static Mod20AnalyticsApiModule_ProvideMod20AnalyticsApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new Mod20AnalyticsApiModule_ProvideMod20AnalyticsApiServiceFactory(retrofitProvider);
  }

  public static Mod20AnalyticsApiService provideMod20AnalyticsApiService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(Mod20AnalyticsApiModule.INSTANCE.provideMod20AnalyticsApiService(retrofit));
  }
}
