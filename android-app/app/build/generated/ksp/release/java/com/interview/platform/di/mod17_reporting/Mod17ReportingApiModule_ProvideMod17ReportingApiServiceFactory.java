package com.interview.platform.di.mod17_reporting;

import com.interview.platform.data.remote.api.Mod17ReportingApiService;
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
public final class Mod17ReportingApiModule_ProvideMod17ReportingApiServiceFactory implements Factory<Mod17ReportingApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public Mod17ReportingApiModule_ProvideMod17ReportingApiServiceFactory(
      Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public Mod17ReportingApiService get() {
    return provideMod17ReportingApiService(retrofitProvider.get());
  }

  public static Mod17ReportingApiModule_ProvideMod17ReportingApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new Mod17ReportingApiModule_ProvideMod17ReportingApiServiceFactory(retrofitProvider);
  }

  public static Mod17ReportingApiService provideMod17ReportingApiService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(Mod17ReportingApiModule.INSTANCE.provideMod17ReportingApiService(retrofit));
  }
}
