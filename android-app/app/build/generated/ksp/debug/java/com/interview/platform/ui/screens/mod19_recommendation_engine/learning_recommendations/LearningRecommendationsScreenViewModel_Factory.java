package com.interview.platform.ui.screens.mod19_recommendation_engine.learning_recommendations;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class LearningRecommendationsScreenViewModel_Factory implements Factory<LearningRecommendationsScreenViewModel> {
  @Override
  public LearningRecommendationsScreenViewModel get() {
    return newInstance();
  }

  public static LearningRecommendationsScreenViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static LearningRecommendationsScreenViewModel newInstance() {
    return new LearningRecommendationsScreenViewModel();
  }

  private static final class InstanceHolder {
    private static final LearningRecommendationsScreenViewModel_Factory INSTANCE = new LearningRecommendationsScreenViewModel_Factory();
  }
}
