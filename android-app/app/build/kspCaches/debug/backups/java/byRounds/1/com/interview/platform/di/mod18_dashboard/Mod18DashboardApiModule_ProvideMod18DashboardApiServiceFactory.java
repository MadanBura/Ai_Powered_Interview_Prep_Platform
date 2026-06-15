package com.interview.platform.di.mod18_dashboard;

import com.interview.platform.data.remote.api.Mod18DashboardApiService;
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
public final class Mod18DashboardApiModule_ProvideMod18DashboardApiServiceFactory implements Factory<Mod18DashboardApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public Mod18DashboardApiModule_ProvideMod18DashboardApiServiceFactory(
      Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public Mod18DashboardApiService get() {
    return provideMod18DashboardApiService(retrofitProvider.get());
  }

  public static Mod18DashboardApiModule_ProvideMod18DashboardApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new Mod18DashboardApiModule_ProvideMod18DashboardApiServiceFactory(retrofitProvider);
  }

  public static Mod18DashboardApiService provideMod18DashboardApiService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(Mod18DashboardApiModule.INSTANCE.provideMod18DashboardApiService(retrofit));
  }
}
