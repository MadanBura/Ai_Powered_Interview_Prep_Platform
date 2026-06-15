package com.interview.platform.di.mod05_experience_level_management;

import com.interview.platform.data.remote.api.Mod05ExperienceLevelManagementApiService;
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
public final class Mod05ExperienceLevelManagementApiModule_ProvideMod05ExperienceLevelManagementApiServiceFactory implements Factory<Mod05ExperienceLevelManagementApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public Mod05ExperienceLevelManagementApiModule_ProvideMod05ExperienceLevelManagementApiServiceFactory(
      Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public Mod05ExperienceLevelManagementApiService get() {
    return provideMod05ExperienceLevelManagementApiService(retrofitProvider.get());
  }

  public static Mod05ExperienceLevelManagementApiModule_ProvideMod05ExperienceLevelManagementApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new Mod05ExperienceLevelManagementApiModule_ProvideMod05ExperienceLevelManagementApiServiceFactory(retrofitProvider);
  }

  public static Mod05ExperienceLevelManagementApiService provideMod05ExperienceLevelManagementApiService(
      Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(Mod05ExperienceLevelManagementApiModule.INSTANCE.provideMod05ExperienceLevelManagementApiService(retrofit));
  }
}
