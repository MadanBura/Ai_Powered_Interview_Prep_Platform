package com.interview.platform.di.mod02_user_profile;

import com.interview.platform.data.remote.api.Mod02UserProfileApiService;
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
public final class Mod02UserProfileApiModule_ProvideMod02UserProfileApiServiceFactory implements Factory<Mod02UserProfileApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public Mod02UserProfileApiModule_ProvideMod02UserProfileApiServiceFactory(
      Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public Mod02UserProfileApiService get() {
    return provideMod02UserProfileApiService(retrofitProvider.get());
  }

  public static Mod02UserProfileApiModule_ProvideMod02UserProfileApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new Mod02UserProfileApiModule_ProvideMod02UserProfileApiServiceFactory(retrofitProvider);
  }

  public static Mod02UserProfileApiService provideMod02UserProfileApiService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(Mod02UserProfileApiModule.INSTANCE.provideMod02UserProfileApiService(retrofit));
  }
}
