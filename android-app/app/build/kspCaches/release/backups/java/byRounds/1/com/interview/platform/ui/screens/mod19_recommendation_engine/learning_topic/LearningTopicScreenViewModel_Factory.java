package com.interview.platform.ui.screens.mod19_recommendation_engine.learning_topic;

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
public final class LearningTopicScreenViewModel_Factory implements Factory<LearningTopicScreenViewModel> {
  private final Provider<RecommendationRepository> recommendationRepositoryProvider;

  public LearningTopicScreenViewModel_Factory(
      Provider<RecommendationRepository> recommendationRepositoryProvider) {
    this.recommendationRepositoryProvider = recommendationRepositoryProvider;
  }

  @Override
  public LearningTopicScreenViewModel get() {
    return newInstance(recommendationRepositoryProvider.get());
  }

  public static LearningTopicScreenViewModel_Factory create(
      Provider<RecommendationRepository> recommendationRepositoryProvider) {
    return new LearningTopicScreenViewModel_Factory(recommendationRepositoryProvider);
  }

  public static LearningTopicScreenViewModel newInstance(
      RecommendationRepository recommendationRepository) {
    return new LearningTopicScreenViewModel(recommendationRepository);
  }
}
