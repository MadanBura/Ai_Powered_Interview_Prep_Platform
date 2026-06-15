package com.interview.platform.ui.screens.mod20_analytics.performance_analytics;

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
public final class PerformanceAnalyticsScreenViewModel_Factory implements Factory<PerformanceAnalyticsScreenViewModel> {
  @Override
  public PerformanceAnalyticsScreenViewModel get() {
    return newInstance();
  }

  public static PerformanceAnalyticsScreenViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static PerformanceAnalyticsScreenViewModel newInstance() {
    return new PerformanceAnalyticsScreenViewModel();
  }

  private static final class InstanceHolder {
    private static final PerformanceAnalyticsScreenViewModel_Factory INSTANCE = new PerformanceAnalyticsScreenViewModel_Factory();
  }
}
