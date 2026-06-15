package com.interview.platform.data.remote;

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
public final class CorrelationIdInterceptor_Factory implements Factory<CorrelationIdInterceptor> {
  @Override
  public CorrelationIdInterceptor get() {
    return newInstance();
  }

  public static CorrelationIdInterceptor_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CorrelationIdInterceptor newInstance() {
    return new CorrelationIdInterceptor();
  }

  private static final class InstanceHolder {
    private static final CorrelationIdInterceptor_Factory INSTANCE = new CorrelationIdInterceptor_Factory();
  }
}
