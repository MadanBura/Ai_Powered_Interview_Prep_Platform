package com.interview.platform.ui.screens.mod19_recommendation_engine.recommended_technologies;

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
public final class RecommendedTechnologiesScreenViewModel_Factory implements Factory<RecommendedTechnologiesScreenViewModel> {
  private final Provider<RecommendationRepository> recommendationRepositoryProvider;

  public RecommendedTechnologiesScreenViewModel_Factory(
      Provider<RecommendationRepository> recommendationRepositoryProvider) {
    this.recommendationRepositoryProvider = recommendationRepositoryProvider;
  }

  @Override
  public RecommendedTechnologiesScreenViewModel get() {
    return newInstance(recommendationRepositoryProvider.get());
  }

  public static RecommendedTechnologiesScreenViewModel_Factory create(
      Provider<RecommendationRepository> recommendationRepositoryProvider) {
    return new RecommendedTechnologiesScreenViewModel_Factory(recommendationRepositoryProvider);
  }

  public static RecommendedTechnologiesScreenViewModel newInstance(
      RecommendationRepository recommendationRepository) {
    return new RecommendedTechnologiesScreenViewModel(recommendationRepository);
  }
}
