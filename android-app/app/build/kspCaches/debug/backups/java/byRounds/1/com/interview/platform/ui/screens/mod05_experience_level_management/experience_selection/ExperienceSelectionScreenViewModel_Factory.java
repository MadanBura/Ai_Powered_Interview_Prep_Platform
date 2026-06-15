package com.interview.platform.ui.screens.mod05_experience_level_management.experience_selection;

import com.interview.platform.data.repository.InterviewSessionManager;
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
public final class ExperienceSelectionScreenViewModel_Factory implements Factory<ExperienceSelectionScreenViewModel> {
  private final Provider<UserPreferencesRepository> userPreferencesRepositoryProvider;

  private final Provider<InterviewSessionManager> sessionManagerProvider;

  public ExperienceSelectionScreenViewModel_Factory(
      Provider<UserPreferencesRepository> userPreferencesRepositoryProvider,
      Provider<InterviewSessionManager> sessionManagerProvider) {
    this.userPreferencesRepositoryProvider = userPreferencesRepositoryProvider;
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public ExperienceSelectionScreenViewModel get() {
    return newInstance(userPreferencesRepositoryProvider.get(), sessionManagerProvider.get());
  }

  public static ExperienceSelectionScreenViewModel_Factory create(
      Provider<UserPreferencesRepository> userPreferencesRepositoryProvider,
      Provider<InterviewSessionManager> sessionManagerProvider) {
    return new ExperienceSelectionScreenViewModel_Factory(userPreferencesRepositoryProvider, sessionManagerProvider);
  }

  public static ExperienceSelectionScreenViewModel newInstance(
      UserPreferencesRepository userPreferencesRepository, InterviewSessionManager sessionManager) {
    return new ExperienceSelectionScreenViewModel(userPreferencesRepository, sessionManager);
  }
}
