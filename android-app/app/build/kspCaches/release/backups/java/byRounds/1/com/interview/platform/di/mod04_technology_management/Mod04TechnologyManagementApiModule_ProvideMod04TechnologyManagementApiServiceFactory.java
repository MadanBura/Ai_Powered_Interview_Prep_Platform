package com.interview.platform.di.mod04_technology_management;

import com.interview.platform.data.remote.api.Mod04TechnologyManagementApiService;
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
public final class Mod04TechnologyManagementApiModule_ProvideMod04TechnologyManagementApiServiceFactory implements Factory<Mod04TechnologyManagementApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public Mod04TechnologyManagementApiModule_ProvideMod04TechnologyManagementApiServiceFactory(
      Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public Mod04TechnologyManagementApiService get() {
    return provideMod04TechnologyManagementApiService(retrofitProvider.get());
  }

  public static Mod04TechnologyManagementApiModule_ProvideMod04TechnologyManagementApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new Mod04TechnologyManagementApiModule_ProvideMod04TechnologyManagementApiServiceFactory(retrofitProvider);
  }

  public static Mod04TechnologyManagementApiService provideMod04TechnologyManagementApiService(
      Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(Mod04TechnologyManagementApiModule.INSTANCE.provideMod04TechnologyManagementApiService(retrofit));
  }
}
