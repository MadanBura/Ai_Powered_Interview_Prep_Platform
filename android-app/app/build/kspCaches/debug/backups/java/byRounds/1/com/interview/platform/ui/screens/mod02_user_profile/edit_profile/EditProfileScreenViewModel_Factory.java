package com.interview.platform.ui.screens.mod02_user_profile.edit_profile;

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
public final class EditProfileScreenViewModel_Factory implements Factory<EditProfileScreenViewModel> {
  @Override
  public EditProfileScreenViewModel get() {
    return newInstance();
  }

  public static EditProfileScreenViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static EditProfileScreenViewModel newInstance() {
    return new EditProfileScreenViewModel();
  }

  private static final class InstanceHolder {
    private static final EditProfileScreenViewModel_Factory INSTANCE = new EditProfileScreenViewModel_Factory();
  }
}
