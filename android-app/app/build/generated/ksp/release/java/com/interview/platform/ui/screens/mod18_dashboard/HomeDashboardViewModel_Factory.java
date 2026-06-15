package com.interview.platform.ui.screens.mod18_dashboard;

import com.interview.platform.data.remote.api.Mod02UserProfileApiService;
import com.interview.platform.data.remote.api.Mod10InterviewSessionApiService;
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
public final class HomeDashboardViewModel_Factory implements Factory<HomeDashboardViewModel> {
  private final Provider<UserPreferencesRepository> userPreferencesRepositoryProvider;

  private final Provider<Mod02UserProfileApiService> profileApiProvider;

  private final Provider<Mod10InterviewSessionApiService> sessionApiProvider;

  private final Provider<InterviewSessionManager> sessionManagerProvider;

  public HomeDashboardViewModel_Factory(
      Provider<UserPreferencesRepository> userPreferencesRepositoryProvider,
      Provider<Mod02UserProfileApiService> profileApiProvider,
      Provider<Mod10InterviewSessionApiService> sessionApiProvider,
      Provider<InterviewSessionManager> sessionManagerProvider) {
    this.userPreferencesRepositoryProvider = userPreferencesRepositoryProvider;
    this.profileApiProvider = profileApiProvider;
    this.sessionApiProvider = sessionApiProvider;
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public HomeDashboardViewModel get() {
    return newInstance(userPreferencesRepositoryProvider.get(), profileApiProvider.get(), sessionApiProvider.get(), sessionManagerProvider.get());
  }

  public static HomeDashboardViewModel_Factory create(
      Provider<UserPreferencesRepository> userPreferencesRepositoryProvider,
      Provider<Mod02UserProfileApiService> profileApiProvider,
      Provider<Mod10InterviewSessionApiService> sessionApiProvider,
      Provider<InterviewSessionManager> sessionManagerProvider) {
    return new HomeDashboardViewModel_Factory(userPreferencesRepositoryProvider, profileApiProvider, sessionApiProvider, sessionManagerProvider);
  }

  public static HomeDashboardViewModel newInstance(
      UserPreferencesRepository userPreferencesRepository, Mod02UserProfileApiService profileApi,
      Mod10InterviewSessionApiService sessionApi, InterviewSessionManager sessionManager) {
    return new HomeDashboardViewModel(userPreferencesRepository, profileApi, sessionApi, sessionManager);
  }
}
