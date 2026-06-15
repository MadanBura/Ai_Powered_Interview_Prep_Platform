package com.interview.platform.data.repository;

import com.interview.platform.data.local.dao.SpeechToTextDao;
import com.interview.platform.data.remote.api.Mod13SpeechToTextApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class SpeechToTextRepositoryImpl_Factory implements Factory<SpeechToTextRepositoryImpl> {
  private final Provider<Mod13SpeechToTextApiService> apiServiceProvider;

  private final Provider<SpeechToTextDao> daoProvider;

  public SpeechToTextRepositoryImpl_Factory(
      Provider<Mod13SpeechToTextApiService> apiServiceProvider,
      Provider<SpeechToTextDao> daoProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.daoProvider = daoProvider;
  }

  @Override
  public SpeechToTextRepositoryImpl get() {
    return newInstance(apiServiceProvider.get(), daoProvider.get());
  }

  public static SpeechToTextRepositoryImpl_Factory create(
      Provider<Mod13SpeechToTextApiService> apiServiceProvider,
      Provider<SpeechToTextDao> daoProvider) {
    return new SpeechToTextRepositoryImpl_Factory(apiServiceProvider, daoProvider);
  }

  public static SpeechToTextRepositoryImpl newInstance(Mod13SpeechToTextApiService apiService,
      SpeechToTextDao dao) {
    return new SpeechToTextRepositoryImpl(apiService, dao);
  }
}
