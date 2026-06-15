package com.interview.platform.ui.screens.mod16_feedback_engine.ai_feedback;

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
public final class AiFeedbackReportScreenViewModel_Factory implements Factory<AiFeedbackReportScreenViewModel> {
  private final Provider<InterviewSessionManager> sessionManagerProvider;

  public AiFeedbackReportScreenViewModel_Factory(
      Provider<InterviewSessionManager> sessionManagerProvider) {
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public AiFeedbackReportScreenViewModel get() {
    return newInstance(sessionManagerProvider.get());
  }

  public static AiFeedbackReportScreenViewModel_Factory create(
      Provider<InterviewSessionManager> sessionManagerProvider) {
    return new AiFeedbackReportScreenViewModel_Factory(sessionManagerProvider);
  }

  public static AiFeedbackReportScreenViewModel newInstance(
      InterviewSessionManager sessionManager) {
    return new AiFeedbackReportScreenViewModel(sessionManager);
  }
}
