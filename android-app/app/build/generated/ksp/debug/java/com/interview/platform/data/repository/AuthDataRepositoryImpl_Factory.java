package com.interview.platform.data.repository;

import com.interview.platform.data.local.dao.AuthTokenDao;
import com.interview.platform.data.remote.api.Mod01AuthenticationApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class AuthDataRepositoryImpl_Factory implements Factory<AuthDataRepositoryImpl> {
  private final Provider<Mod01AuthenticationApiService> apiServiceProvider;

  private final Provider<AuthTokenDao> daoProvider;

  public AuthDataRepositoryImpl_Factory(Provider<Mod01AuthenticationApiService> apiServiceProvider,
      Provider<AuthTokenDao> daoProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.daoProvider = daoProvider;
  }

  @Override
  public AuthDataRepositoryImpl get() {
    return newInstance(apiServiceProvider.get(), daoProvider.get());
  }

  public static AuthDataRepositoryImpl_Factory create(
      Provider<Mod01AuthenticationApiService> apiServiceProvider,
      Provider<AuthTokenDao> daoProvider) {
    return new AuthDataRepositoryImpl_Factory(apiServiceProvider, daoProvider);
  }

  public static AuthDataRepositoryImpl newInstance(Mod01AuthenticationApiService apiService,
      AuthTokenDao dao) {
    return new AuthDataRepositoryImpl(apiService, dao);
  }
}
