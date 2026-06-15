package com.interview.platform.di.mod01_authentication;

import com.interview.platform.data.remote.api.Mod01AuthenticationApiService;
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
public final class Mod01AuthenticationApiModule_ProvideMod01AuthenticationApiServiceFactory implements Factory<Mod01AuthenticationApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public Mod01AuthenticationApiModule_ProvideMod01AuthenticationApiServiceFactory(
      Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public Mod01AuthenticationApiService get() {
    return provideMod01AuthenticationApiService(retrofitProvider.get());
  }

  public static Mod01AuthenticationApiModule_ProvideMod01AuthenticationApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new Mod01AuthenticationApiModule_ProvideMod01AuthenticationApiServiceFactory(retrofitProvider);
  }

  public static Mod01AuthenticationApiService provideMod01AuthenticationApiService(
      Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(Mod01AuthenticationApiModule.INSTANCE.provideMod01AuthenticationApiService(retrofit));
  }
}
