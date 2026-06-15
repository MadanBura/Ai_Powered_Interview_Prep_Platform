package com.interview.platform.di.mod13_speech_to_text;

import com.interview.platform.data.remote.api.Mod13SpeechToTextApiService;
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
public final class Mod13SpeechToTextApiModule_ProvideMod13SpeechToTextApiServiceFactory implements Factory<Mod13SpeechToTextApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public Mod13SpeechToTextApiModule_ProvideMod13SpeechToTextApiServiceFactory(
      Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public Mod13SpeechToTextApiService get() {
    return provideMod13SpeechToTextApiService(retrofitProvider.get());
  }

  public static Mod13SpeechToTextApiModule_ProvideMod13SpeechToTextApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new Mod13SpeechToTextApiModule_ProvideMod13SpeechToTextApiServiceFactory(retrofitProvider);
  }

  public static Mod13SpeechToTextApiService provideMod13SpeechToTextApiService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(Mod13SpeechToTextApiModule.INSTANCE.provideMod13SpeechToTextApiService(retrofit));
  }
}
