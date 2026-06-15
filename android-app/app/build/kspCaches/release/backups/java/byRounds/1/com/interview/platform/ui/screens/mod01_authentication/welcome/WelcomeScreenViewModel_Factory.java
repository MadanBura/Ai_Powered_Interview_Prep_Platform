package com.interview.platform.ui.screens.mod01_authentication.welcome;

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
public final class WelcomeScreenViewModel_Factory implements Factory<WelcomeScreenViewModel> {
  @Override
  public WelcomeScreenViewModel get() {
    return newInstance();
  }

  public static WelcomeScreenViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static WelcomeScreenViewModel newInstance() {
    return new WelcomeScreenViewModel();
  }

  private static final class InstanceHolder {
    private static final WelcomeScreenViewModel_Factory INSTANCE = new WelcomeScreenViewModel_Factory();
  }
}
