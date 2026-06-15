package com.interview.platform.data.repository;

import com.interview.platform.data.local.dao.VoiceRecordingDao;
import com.interview.platform.data.remote.api.Mod12VoiceRecordingApiService;
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
public final class VoiceRecordingRepositoryImpl_Factory implements Factory<VoiceRecordingRepositoryImpl> {
  private final Provider<Mod12VoiceRecordingApiService> apiServiceProvider;

  private final Provider<VoiceRecordingDao> daoProvider;

  public VoiceRecordingRepositoryImpl_Factory(
      Provider<Mod12VoiceRecordingApiService> apiServiceProvider,
      Provider<VoiceRecordingDao> daoProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.daoProvider = daoProvider;
  }

  @Override
  public VoiceRecordingRepositoryImpl get() {
    return newInstance(apiServiceProvider.get(), daoProvider.get());
  }

  public static VoiceRecordingRepositoryImpl_Factory create(
      Provider<Mod12VoiceRecordingApiService> apiServiceProvider,
      Provider<VoiceRecordingDao> daoProvider) {
    return new VoiceRecordingRepositoryImpl_Factory(apiServiceProvider, daoProvider);
  }

  public static VoiceRecordingRepositoryImpl newInstance(Mod12VoiceRecordingApiService apiService,
      VoiceRecordingDao dao) {
    return new VoiceRecordingRepositoryImpl(apiService, dao);
  }
}
