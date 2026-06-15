package com.interview.platform.ui.screens.mod01_authentication.otp_verification;

import com.interview.platform.data.remote.TokenProvider;
import com.interview.platform.data.remote.api.Mod01AuthenticationApiService;
import com.interview.platform.data.remote.api.Mod02UserProfileApiService;
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
public final class OtpVerificationScreenViewModel_Factory implements Factory<OtpVerificationScreenViewModel> {
  private final Provider<Mod01AuthenticationApiService> authApiProvider;

  private final Provider<Mod02UserProfileApiService> profileApiProvider;

  private final Provider<UserPreferencesRepository> userPreferencesRepositoryProvider;

  private final Provider<TokenProvider> tokenProvider;

  public OtpVerificationScreenViewModel_Factory(
      Provider<Mod01AuthenticationApiService> authApiProvider,
      Provider<Mod02UserProfileApiService> profileApiProvider,
      Provider<UserPreferencesRepository> userPreferencesRepositoryProvider,
      Provider<TokenProvider> tokenProvider) {
    this.authApiProvider = authApiProvider;
    this.profileApiProvider = profileApiProvider;
    this.userPreferencesRepositoryProvider = userPreferencesRepositoryProvider;
    this.tokenProvider = tokenProvider;
  }

  @Override
  public OtpVerificationScreenViewModel get() {
    return newInstance(authApiProvider.get(), profileApiProvider.get(), userPreferencesRepositoryProvider.get(), tokenProvider.get());
  }

  public static OtpVerificationScreenViewModel_Factory create(
      Provider<Mod01AuthenticationApiService> authApiProvider,
      Provider<Mod02UserProfileApiService> profileApiProvider,
      Provider<UserPreferencesRepository> userPreferencesRepositoryProvider,
      Provider<TokenProvider> tokenProvider) {
    return new OtpVerificationScreenViewModel_Factory(authApiProvider, profileApiProvider, userPreferencesRepositoryProvider, tokenProvider);
  }

  public static OtpVerificationScreenViewModel newInstance(Mod01AuthenticationApiService authApi,
      Mod02UserProfileApiService profileApi, UserPreferencesRepository userPreferencesRepository,
      TokenProvider tokenProvider) {
    return new OtpVerificationScreenViewModel(authApi, profileApi, userPreferencesRepository, tokenProvider);
  }
}
