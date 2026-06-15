package com.interview.platform.ui.screens.mod09_interview_configuration.question_count_selection;

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
public final class QuestionCountSelectionScreenViewModel_Factory implements Factory<QuestionCountSelectionScreenViewModel> {
  private final Provider<InterviewSessionManager> sessionManagerProvider;

  public QuestionCountSelectionScreenViewModel_Factory(
      Provider<InterviewSessionManager> sessionManagerProvider) {
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public QuestionCountSelectionScreenViewModel get() {
    return newInstance(sessionManagerProvider.get());
  }

  public static QuestionCountSelectionScreenViewModel_Factory create(
      Provider<InterviewSessionManager> sessionManagerProvider) {
    return new QuestionCountSelectionScreenViewModel_Factory(sessionManagerProvider);
  }

  public static QuestionCountSelectionScreenViewModel newInstance(
      InterviewSessionManager sessionManager) {
    return new QuestionCountSelectionScreenViewModel(sessionManager);
  }
}
