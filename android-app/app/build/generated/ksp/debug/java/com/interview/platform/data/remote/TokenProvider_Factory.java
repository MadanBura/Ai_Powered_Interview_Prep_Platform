package com.interview.platform.data.remote;

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
public final class TokenProvider_Factory implements Factory<TokenProvider> {
  @Override
  public TokenProvider get() {
    return newInstance();
  }

  public static TokenProvider_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static TokenProvider newInstance() {
    return new TokenProvider();
  }

  private static final class InstanceHolder {
    private static final TokenProvider_Factory INSTANCE = new TokenProvider_Factory();
  }
}
