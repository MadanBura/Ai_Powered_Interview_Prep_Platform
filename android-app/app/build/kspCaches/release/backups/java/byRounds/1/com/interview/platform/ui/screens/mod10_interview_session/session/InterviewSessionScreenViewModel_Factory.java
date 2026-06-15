package com.interview.platform.ui.screens.mod10_interview_session.session;

import com.interview.platform.data.remote.api.Mod10InterviewSessionApiService;
import com.interview.platform.data.remote.api.Mod11QuestionDeliveryApiService;
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
public final class InterviewSessionScreenViewModel_Factory implements Factory<InterviewSessionScreenViewModel> {
  private final Provider<Mod11QuestionDeliveryApiService> questionApiProvider;

  private final Provider<Mod10InterviewSessionApiService> sessionApiProvider;

  private final Provider<InterviewSessionManager> sessionManagerProvider;

  public InterviewSessionScreenViewModel_Factory(
      Provider<Mod11QuestionDeliveryApiService> questionApiProvider,
      Provider<Mod10InterviewSessionApiService> sessionApiProvider,
      Provider<InterviewSessionManager> sessionManagerProvider) {
    this.questionApiProvider = questionApiProvider;
    this.sessionApiProvider = sessionApiProvider;
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public InterviewSessionScreenViewModel get() {
    return newInstance(questionApiProvider.get(), sessionApiProvider.get(), sessionManagerProvider.get());
  }

  public static InterviewSessionScreenViewModel_Factory create(
      Provider<Mod11QuestionDeliveryApiService> questionApiProvider,
      Provider<Mod10InterviewSessionApiService> sessionApiProvider,
      Provider<InterviewSessionManager> sessionManagerProvider) {
    return new InterviewSessionScreenViewModel_Factory(questionApiProvider, sessionApiProvider, sessionManagerProvider);
  }

  public static InterviewSessionScreenViewModel newInstance(
      Mod11QuestionDeliveryApiService questionApi, Mod10InterviewSessionApiService sessionApi,
      InterviewSessionManager sessionManager) {
    return new InterviewSessionScreenViewModel(questionApi, sessionApi, sessionManager);
  }
}
