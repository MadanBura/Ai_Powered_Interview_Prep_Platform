package com.interview.platform.ui.screens.mod04_technology_management.technology_selection;

import com.interview.platform.data.remote.api.Mod02UserProfileApiService;
import com.interview.platform.data.remote.api.Mod04TechnologyManagementApiService;
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
public final class TechnologySelectionScreenViewModel_Factory implements Factory<TechnologySelectionScreenViewModel> {
  private final Provider<UserPreferencesRepository> userPreferencesRepositoryProvider;

  private final Provider<InterviewSessionManager> sessionManagerProvider;

  private final Provider<Mod02UserProfileApiService> userProfileApiServiceProvider;

  private final Provider<Mod04TechnologyManagementApiService> technologyApiServiceProvider;

  public TechnologySelectionScreenViewModel_Factory(
      Provider<UserPreferencesRepository> userPreferencesRepositoryProvider,
      Provider<InterviewSessionManager> sessionManagerProvider,
      Provider<Mod02UserProfileApiService> userProfileApiServiceProvider,
      Provider<Mod04TechnologyManagementApiService> technologyApiServiceProvider) {
    this.userPreferencesRepositoryProvider = userPreferencesRepositoryProvider;
    this.sessionManagerProvider = sessionManagerProvider;
    this.userProfileApiServiceProvider = userProfileApiServiceProvider;
    this.technologyApiServiceProvider = technologyApiServiceProvider;
  }

  @Override
  public TechnologySelectionScreenViewModel get() {
    return newInstance(userPreferencesRepositoryProvider.get(), sessionManagerProvider.get(), userProfileApiServiceProvider.get(), technologyApiServiceProvider.get());
  }

  public static TechnologySelectionScreenViewModel_Factory create(
      Provider<UserPreferencesRepository> userPreferencesRepositoryProvider,
      Provider<InterviewSessionManager> sessionManagerProvider,
      Provider<Mod02UserProfileApiService> userProfileApiServiceProvider,
      Provider<Mod04TechnologyManagementApiService> technologyApiServiceProvider) {
    return new TechnologySelectionScreenViewModel_Factory(userPreferencesRepositoryProvider, sessionManagerProvider, userProfileApiServiceProvider, technologyApiServiceProvider);
  }

  public static TechnologySelectionScreenViewModel newInstance(
      UserPreferencesRepository userPreferencesRepository, InterviewSessionManager sessionManager,
      Mod02UserProfileApiService userProfileApiService,
      Mod04TechnologyManagementApiService technologyApiService) {
    return new TechnologySelectionScreenViewModel(userPreferencesRepository, sessionManager, userProfileApiService, technologyApiService);
  }
}
