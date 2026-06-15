package com.interview.platform.ui.screens.mod01_authentication.login;

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
public final class LoginScreenViewModel_Factory implements Factory<LoginScreenViewModel> {
  private final Provider<Mod01AuthenticationApiService> authApiProvider;

  public LoginScreenViewModel_Factory(Provider<Mod01AuthenticationApiService> authApiProvider) {
    this.authApiProvider = authApiProvider;
  }

  @Override
  public LoginScreenViewModel get() {
    return newInstance(authApiProvider.get());
  }

  public static LoginScreenViewModel_Factory create(
      Provider<Mod01AuthenticationApiService> authApiProvider) {
    return new LoginScreenViewModel_Factory(authApiProvider);
  }

  public static LoginScreenViewModel newInstance(Mod01AuthenticationApiService authApi) {
    return new LoginScreenViewModel(authApi);
  }
}
