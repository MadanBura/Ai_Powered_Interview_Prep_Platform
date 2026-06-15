package com.interview.platform.ui.screens.mod18_dashboard.achievements;

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
public final class AchievementsScreenViewModel_Factory implements Factory<AchievementsScreenViewModel> {
  @Override
  public AchievementsScreenViewModel get() {
    return newInstance();
  }

  public static AchievementsScreenViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AchievementsScreenViewModel newInstance() {
    return new AchievementsScreenViewModel();
  }

  private static final class InstanceHolder {
    private static final AchievementsScreenViewModel_Factory INSTANCE = new AchievementsScreenViewModel_Factory();
  }
}
