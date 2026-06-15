package com.interview.platform.ui.screens.mod19_recommendation_engine.practice_plan;

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
public final class PracticePlanScreenViewModel_Factory implements Factory<PracticePlanScreenViewModel> {
  @Override
  public PracticePlanScreenViewModel get() {
    return newInstance();
  }

  public static PracticePlanScreenViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static PracticePlanScreenViewModel newInstance() {
    return new PracticePlanScreenViewModel();
  }

  private static final class InstanceHolder {
    private static final PracticePlanScreenViewModel_Factory INSTANCE = new PracticePlanScreenViewModel_Factory();
  }
}
