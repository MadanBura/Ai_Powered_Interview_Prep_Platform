package com.interview.platform.ui.screens.mod01_authentication.splash;

import com.interview.platform.data.remote.TokenProvider;
import com.interview.platform.data.repository.UserPreferencesRepository;
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
public final class SplashScreenViewModel_Factory implements Factory<SplashScreenViewModel> {
  private final Provider<UserPreferencesRepository> userPreferencesRepositoryProvider;

  private final Provider<TokenProvider> tokenProvider;

  public SplashScreenViewModel_Factory(
      Provider<UserPreferencesRepository> userPreferencesRepositoryProvider,
      Provider<TokenProvider> tokenProvider) {
    this.userPreferencesRepositoryProvider = userPreferencesRepositoryProvider;
    this.tokenProvider = tokenProvider;
  }

  @Override
  public SplashScreenViewModel get() {
    return newInstance(userPreferencesRepositoryProvider.get(), tokenProvider.get());
  }

  public static SplashScreenViewModel_Factory create(
      Provider<UserPreferencesRepository> userPreferencesRepositoryProvider,
      Provider<TokenProvider> tokenProvider) {
    return new SplashScreenViewModel_Factory(userPreferencesRepositoryProvider, tokenProvider);
  }

  public static SplashScreenViewModel newInstance(
      UserPreferencesRepository userPreferencesRepository, TokenProvider tokenProvider) {
    return new SplashScreenViewModel(userPreferencesRepository, tokenProvider);
  }
}
