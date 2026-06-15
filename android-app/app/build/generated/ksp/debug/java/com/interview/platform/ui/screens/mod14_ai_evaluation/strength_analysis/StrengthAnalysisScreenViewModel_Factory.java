package com.interview.platform.ui.screens.mod14_ai_evaluation.strength_analysis;

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
public final class StrengthAnalysisScreenViewModel_Factory implements Factory<StrengthAnalysisScreenViewModel> {
  private final Provider<InterviewSessionManager> sessionManagerProvider;

  public StrengthAnalysisScreenViewModel_Factory(
      Provider<InterviewSessionManager> sessionManagerProvider) {
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public StrengthAnalysisScreenViewModel get() {
    return newInstance(sessionManagerProvider.get());
  }

  public static StrengthAnalysisScreenViewModel_Factory create(
      Provider<InterviewSessionManager> sessionManagerProvider) {
    return new StrengthAnalysisScreenViewModel_Factory(sessionManagerProvider);
  }

  public static StrengthAnalysisScreenViewModel newInstance(
      InterviewSessionManager sessionManager) {
    return new StrengthAnalysisScreenViewModel(sessionManager);
  }
}
