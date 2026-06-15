package com.interview.platform.ui.screens.mod01_authentication.permissions;

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
public final class PermissionsScreenViewModel_Factory implements Factory<PermissionsScreenViewModel> {
  @Override
  public PermissionsScreenViewModel get() {
    return newInstance();
  }

  public static PermissionsScreenViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static PermissionsScreenViewModel newInstance() {
    return new PermissionsScreenViewModel();
  }

  private static final class InstanceHolder {
    private static final PermissionsScreenViewModel_Factory INSTANCE = new PermissionsScreenViewModel_Factory();
  }
}
