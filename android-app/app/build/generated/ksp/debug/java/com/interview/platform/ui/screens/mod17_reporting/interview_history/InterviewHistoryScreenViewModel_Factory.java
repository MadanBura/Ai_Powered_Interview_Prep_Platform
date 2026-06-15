package com.interview.platform.ui.screens.mod17_reporting.interview_history;

import com.interview.platform.data.remote.api.Mod10InterviewSessionApiService;
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
public final class InterviewHistoryScreenViewModel_Factory implements Factory<InterviewHistoryScreenViewModel> {
  private final Provider<Mod10InterviewSessionApiService> sessionApiProvider;

  public InterviewHistoryScreenViewModel_Factory(
      Provider<Mod10InterviewSessionApiService> sessionApiProvider) {
    this.sessionApiProvider = sessionApiProvider;
  }

  @Override
  public InterviewHistoryScreenViewModel get() {
    return newInstance(sessionApiProvider.get());
  }

  public static InterviewHistoryScreenViewModel_Factory create(
      Provider<Mod10InterviewSessionApiService> sessionApiProvider) {
    return new InterviewHistoryScreenViewModel_Factory(sessionApiProvider);
  }

  public static InterviewHistoryScreenViewModel newInstance(
      Mod10InterviewSessionApiService sessionApi) {
    return new InterviewHistoryScreenViewModel(sessionApi);
  }
}
