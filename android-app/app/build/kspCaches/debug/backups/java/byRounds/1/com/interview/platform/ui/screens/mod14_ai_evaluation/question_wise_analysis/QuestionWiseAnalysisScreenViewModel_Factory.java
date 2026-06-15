package com.interview.platform.ui.screens.mod14_ai_evaluation.question_wise_analysis;

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
public final class QuestionWiseAnalysisScreenViewModel_Factory implements Factory<QuestionWiseAnalysisScreenViewModel> {
  private final Provider<InterviewSessionManager> sessionManagerProvider;

  public QuestionWiseAnalysisScreenViewModel_Factory(
      Provider<InterviewSessionManager> sessionManagerProvider) {
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public QuestionWiseAnalysisScreenViewModel get() {
    return newInstance(sessionManagerProvider.get());
  }

  public static QuestionWiseAnalysisScreenViewModel_Factory create(
      Provider<InterviewSessionManager> sessionManagerProvider) {
    return new QuestionWiseAnalysisScreenViewModel_Factory(sessionManagerProvider);
  }

  public static QuestionWiseAnalysisScreenViewModel newInstance(
      InterviewSessionManager sessionManager) {
    return new QuestionWiseAnalysisScreenViewModel(sessionManager);
  }
}
