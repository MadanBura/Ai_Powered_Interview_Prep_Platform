package com.interview.platform.ui.screens.mod14_ai_evaluation.results_dashboard;

import com.interview.platform.data.remote.api.Mod14AiEvaluationApiService;
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
public final class ResultsDashboardScreenViewModel_Factory implements Factory<ResultsDashboardScreenViewModel> {
  private final Provider<Mod14AiEvaluationApiService> evaluationApiProvider;

  private final Provider<InterviewSessionManager> sessionManagerProvider;

  public ResultsDashboardScreenViewModel_Factory(
      Provider<Mod14AiEvaluationApiService> evaluationApiProvider,
      Provider<InterviewSessionManager> sessionManagerProvider) {
    this.evaluationApiProvider = evaluationApiProvider;
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public ResultsDashboardScreenViewModel get() {
    return newInstance(evaluationApiProvider.get(), sessionManagerProvider.get());
  }

  public static ResultsDashboardScreenViewModel_Factory create(
      Provider<Mod14AiEvaluationApiService> evaluationApiProvider,
      Provider<InterviewSessionManager> sessionManagerProvider) {
    return new ResultsDashboardScreenViewModel_Factory(evaluationApiProvider, sessionManagerProvider);
  }

  public static ResultsDashboardScreenViewModel newInstance(
      Mod14AiEvaluationApiService evaluationApi, InterviewSessionManager sessionManager) {
    return new ResultsDashboardScreenViewModel(evaluationApi, sessionManager);
  }
}
