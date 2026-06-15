package com.interview.platform.ui.screens.mod10_interview_session.completed;

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
public final class InterviewCompletedScreenViewModel_Factory implements Factory<InterviewCompletedScreenViewModel> {
  @Override
  public InterviewCompletedScreenViewModel get() {
    return newInstance();
  }

  public static InterviewCompletedScreenViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static InterviewCompletedScreenViewModel newInstance() {
    return new InterviewCompletedScreenViewModel();
  }

  private static final class InstanceHolder {
    private static final InterviewCompletedScreenViewModel_Factory INSTANCE = new InterviewCompletedScreenViewModel_Factory();
  }
}
