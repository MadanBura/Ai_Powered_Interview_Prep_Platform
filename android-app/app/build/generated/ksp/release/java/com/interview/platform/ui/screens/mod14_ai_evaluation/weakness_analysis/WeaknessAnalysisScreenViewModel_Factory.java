package com.interview.platform.ui.screens.mod14_ai_evaluation.weakness_analysis;

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
public final class WeaknessAnalysisScreenViewModel_Factory implements Factory<WeaknessAnalysisScreenViewModel> {
  private final Provider<InterviewSessionManager> sessionManagerProvider;

  public WeaknessAnalysisScreenViewModel_Factory(
      Provider<InterviewSessionManager> sessionManagerProvider) {
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public WeaknessAnalysisScreenViewModel get() {
    return newInstance(sessionManagerProvider.get());
  }

  public static WeaknessAnalysisScreenViewModel_Factory create(
      Provider<InterviewSessionManager> sessionManagerProvider) {
    return new WeaknessAnalysisScreenViewModel_Factory(sessionManagerProvider);
  }

  public static WeaknessAnalysisScreenViewModel newInstance(
      InterviewSessionManager sessionManager) {
    return new WeaknessAnalysisScreenViewModel(sessionManager);
  }
}
