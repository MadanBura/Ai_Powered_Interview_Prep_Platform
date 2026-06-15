package com.interview.platform.ui.screens.mod09_interview_configuration.interview_summary;

import com.interview.platform.data.remote.api.Mod10InterviewSessionApiService;
import com.interview.platform.data.repository.InterviewSessionManager;
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
public final class InterviewSummaryScreenViewModel_Factory implements Factory<InterviewSummaryScreenViewModel> {
  private final Provider<Mod10InterviewSessionApiService> sessionApiProvider;

  private final Provider<InterviewSessionManager> sessionManagerProvider;

  public InterviewSummaryScreenViewModel_Factory(
      Provider<Mod10InterviewSessionApiService> sessionApiProvider,
      Provider<InterviewSessionManager> sessionManagerProvider) {
    this.sessionApiProvider = sessionApiProvider;
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public InterviewSummaryScreenViewModel get() {
    return newInstance(sessionApiProvider.get(), sessionManagerProvider.get());
  }

  public static InterviewSummaryScreenViewModel_Factory create(
      Provider<Mod10InterviewSessionApiService> sessionApiProvider,
      Provider<InterviewSessionManager> sessionManagerProvider) {
    return new InterviewSummaryScreenViewModel_Factory(sessionApiProvider, sessionManagerProvider);
  }

  public static InterviewSummaryScreenViewModel newInstance(
      Mod10InterviewSessionApiService sessionApi, InterviewSessionManager sessionManager) {
    return new InterviewSummaryScreenViewModel(sessionApi, sessionManager);
  }
}
