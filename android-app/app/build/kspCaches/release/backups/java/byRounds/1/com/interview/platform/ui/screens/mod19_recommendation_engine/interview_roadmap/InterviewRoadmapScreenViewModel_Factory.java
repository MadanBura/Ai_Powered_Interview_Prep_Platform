package com.interview.platform.ui.screens.mod19_recommendation_engine.interview_roadmap;

import com.interview.platform.data.remote.api.Mod19RecommendationEngineApiService;
import com.interview.platform.data.repository.UserPreferencesRepository;
import com.interview.platform.domain.repository.RecommendationRepository;
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
public final class InterviewRoadmapScreenViewModel_Factory implements Factory<InterviewRoadmapScreenViewModel> {
  private final Provider<Mod19RecommendationEngineApiService> apiServiceProvider;

  private final Provider<UserPreferencesRepository> userPreferencesRepositoryProvider;

  private final Provider<RecommendationRepository> recommendationRepositoryProvider;

  public InterviewRoadmapScreenViewModel_Factory(
      Provider<Mod19RecommendationEngineApiService> apiServiceProvider,
      Provider<UserPreferencesRepository> userPreferencesRepositoryProvider,
      Provider<RecommendationRepository> recommendationRepositoryProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.userPreferencesRepositoryProvider = userPreferencesRepositoryProvider;
    this.recommendationRepositoryProvider = recommendationRepositoryProvider;
  }

  @Override
  public InterviewRoadmapScreenViewModel get() {
    return newInstance(apiServiceProvider.get(), userPreferencesRepositoryProvider.get(), recommendationRepositoryProvider.get());
  }

  public static InterviewRoadmapScreenViewModel_Factory create(
      Provider<Mod19RecommendationEngineApiService> apiServiceProvider,
      Provider<UserPreferencesRepository> userPreferencesRepositoryProvider,
      Provider<RecommendationRepository> recommendationRepositoryProvider) {
    return new InterviewRoadmapScreenViewModel_Factory(apiServiceProvider, userPreferencesRepositoryProvider, recommendationRepositoryProvider);
  }

  public static InterviewRoadmapScreenViewModel newInstance(
      Mod19RecommendationEngineApiService apiService,
      UserPreferencesRepository userPreferencesRepository,
      RecommendationRepository recommendationRepository) {
    return new InterviewRoadmapScreenViewModel(apiService, userPreferencesRepository, recommendationRepository);
  }
}
