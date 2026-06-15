package com.interview.platform.ui.screens.mod03_department_management.department_selection;

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
public final class DepartmentSelectionScreenViewModel_Factory implements Factory<DepartmentSelectionScreenViewModel> {
  @Override
  public DepartmentSelectionScreenViewModel get() {
    return newInstance();
  }

  public static DepartmentSelectionScreenViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static DepartmentSelectionScreenViewModel newInstance() {
    return new DepartmentSelectionScreenViewModel();
  }

  private static final class InstanceHolder {
    private static final DepartmentSelectionScreenViewModel_Factory INSTANCE = new DepartmentSelectionScreenViewModel_Factory();
  }
}
