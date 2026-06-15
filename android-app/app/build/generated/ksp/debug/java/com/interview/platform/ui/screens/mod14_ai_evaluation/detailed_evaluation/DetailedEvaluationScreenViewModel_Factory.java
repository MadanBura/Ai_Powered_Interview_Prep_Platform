package com.interview.platform.ui.screens.mod14_ai_evaluation.detailed_evaluation;

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
public final class DetailedEvaluationScreenViewModel_Factory implements Factory<DetailedEvaluationScreenViewModel> {
  private final Provider<InterviewSessionManager> sessionManagerProvider;

  public DetailedEvaluationScreenViewModel_Factory(
      Provider<InterviewSessionManager> sessionManagerProvider) {
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public DetailedEvaluationScreenViewModel get() {
    return newInstance(sessionManagerProvider.get());
  }

  public static DetailedEvaluationScreenViewModel_Factory create(
      Provider<InterviewSessionManager> sessionManagerProvider) {
    return new DetailedEvaluationScreenViewModel_Factory(sessionManagerProvider);
  }

  public static DetailedEvaluationScreenViewModel newInstance(
      InterviewSessionManager sessionManager) {
    return new DetailedEvaluationScreenViewModel(sessionManager);
  }
}
