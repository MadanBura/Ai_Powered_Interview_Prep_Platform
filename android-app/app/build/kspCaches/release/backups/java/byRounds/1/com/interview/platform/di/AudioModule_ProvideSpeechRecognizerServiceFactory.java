package com.interview.platform.di;

import android.content.Context;
import com.interview.platform.domain.audio.SpeechRecognizerService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class AudioModule_ProvideSpeechRecognizerServiceFactory implements Factory<SpeechRecognizerService> {
  private final Provider<Context> contextProvider;

  public AudioModule_ProvideSpeechRecognizerServiceFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public SpeechRecognizerService get() {
    return provideSpeechRecognizerService(contextProvider.get());
  }

  public static AudioModule_ProvideSpeechRecognizerServiceFactory create(
      Provider<Context> contextProvider) {
    return new AudioModule_ProvideSpeechRecognizerServiceFactory(contextProvider);
  }

  public static SpeechRecognizerService provideSpeechRecognizerService(Context context) {
    return Preconditions.checkNotNullFromProvides(AudioModule.INSTANCE.provideSpeechRecognizerService(context));
  }
}
