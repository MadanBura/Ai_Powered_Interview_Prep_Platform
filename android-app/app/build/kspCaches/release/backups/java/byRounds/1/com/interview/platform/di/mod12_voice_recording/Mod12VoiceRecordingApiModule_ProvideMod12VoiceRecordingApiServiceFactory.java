package com.interview.platform.di.mod12_voice_recording;

import com.interview.platform.data.remote.api.Mod12VoiceRecordingApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

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
public final class Mod12VoiceRecordingApiModule_ProvideMod12VoiceRecordingApiServiceFactory implements Factory<Mod12VoiceRecordingApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public Mod12VoiceRecordingApiModule_ProvideMod12VoiceRecordingApiServiceFactory(
      Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public Mod12VoiceRecordingApiService get() {
    return provideMod12VoiceRecordingApiService(retrofitProvider.get());
  }

  public static Mod12VoiceRecordingApiModule_ProvideMod12VoiceRecordingApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new Mod12VoiceRecordingApiModule_ProvideMod12VoiceRecordingApiServiceFactory(retrofitProvider);
  }

  public static Mod12VoiceRecordingApiService provideMod12VoiceRecordingApiService(
      Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(Mod12VoiceRecordingApiModule.INSTANCE.provideMod12VoiceRecordingApiService(retrofit));
  }
}
