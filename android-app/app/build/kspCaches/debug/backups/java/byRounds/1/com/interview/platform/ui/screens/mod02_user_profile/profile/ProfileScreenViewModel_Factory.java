package com.interview.platform.ui.screens.mod02_user_profile.profile;

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
public final class ProfileScreenViewModel_Factory implements Factory<ProfileScreenViewModel> {
  private final Provider<UserPreferencesRepository> userPreferencesRepositoryProvider;

  public ProfileScreenViewModel_Factory(
      Provider<UserPreferencesRepository> userPreferencesRepositoryProvider) {
    this.userPreferencesRepositoryProvider = userPreferencesRepositoryProvider;
  }

  @Override
  public ProfileScreenViewModel get() {
    return newInstance(userPreferencesRepositoryProvider.get());
  }

  public static ProfileScreenViewModel_Factory create(
      Provider<UserPreferencesRepository> userPreferencesRepositoryProvider) {
    return new ProfileScreenViewModel_Factory(userPreferencesRepositoryProvider);
  }

  public static ProfileScreenViewModel newInstance(
      UserPreferencesRepository userPreferencesRepository) {
    return new ProfileScreenViewModel(userPreferencesRepository);
  }
}
