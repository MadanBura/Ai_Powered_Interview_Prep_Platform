package com.interview.platform.ui.screens.mod09_interview_configuration.home;

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
public final class HomeDashboardScreenViewModel_Factory implements Factory<HomeDashboardScreenViewModel> {
  @Override
  public HomeDashboardScreenViewModel get() {
    return newInstance();
  }

  public static HomeDashboardScreenViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static HomeDashboardScreenViewModel newInstance() {
    return new HomeDashboardScreenViewModel();
  }

  private static final class InstanceHolder {
    private static final HomeDashboardScreenViewModel_Factory INSTANCE = new HomeDashboardScreenViewModel_Factory();
  }
}
