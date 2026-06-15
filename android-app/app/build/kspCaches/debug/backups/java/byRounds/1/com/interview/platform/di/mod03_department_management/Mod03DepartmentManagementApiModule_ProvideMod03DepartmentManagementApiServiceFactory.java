package com.interview.platform.di.mod03_department_management;

import com.interview.platform.data.remote.api.Mod03DepartmentManagementApiService;
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
public final class Mod03DepartmentManagementApiModule_ProvideMod03DepartmentManagementApiServiceFactory implements Factory<Mod03DepartmentManagementApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public Mod03DepartmentManagementApiModule_ProvideMod03DepartmentManagementApiServiceFactory(
      Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public Mod03DepartmentManagementApiService get() {
    return provideMod03DepartmentManagementApiService(retrofitProvider.get());
  }

  public static Mod03DepartmentManagementApiModule_ProvideMod03DepartmentManagementApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new Mod03DepartmentManagementApiModule_ProvideMod03DepartmentManagementApiServiceFactory(retrofitProvider);
  }

  public static Mod03DepartmentManagementApiService provideMod03DepartmentManagementApiService(
      Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(Mod03DepartmentManagementApiModule.INSTANCE.provideMod03DepartmentManagementApiService(retrofit));
  }
}
