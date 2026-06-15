package com.interview.platform.data.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class InterviewSessionManager_Factory implements Factory<InterviewSessionManager> {
  @Override
  public InterviewSessionManager get() {
    return newInstance();
  }

  public static InterviewSessionManager_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static InterviewSessionManager newInstance() {
    return new InterviewSessionManager();
  }

  private static final class InstanceHolder {
    private static final InterviewSessionManager_Factory INSTANCE = new InterviewSessionManager_Factory();
  }
}
