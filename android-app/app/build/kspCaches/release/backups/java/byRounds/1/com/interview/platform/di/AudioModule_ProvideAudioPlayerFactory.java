package com.interview.platform.di;

import android.content.Context;
import com.interview.platform.domain.audio.AudioPlayer;
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
public final class AudioModule_ProvideAudioPlayerFactory implements Factory<AudioPlayer> {
  private final Provider<Context> contextProvider;

  public AudioModule_ProvideAudioPlayerFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public AudioPlayer get() {
    return provideAudioPlayer(contextProvider.get());
  }

  public static AudioModule_ProvideAudioPlayerFactory create(Provider<Context> contextProvider) {
    return new AudioModule_ProvideAudioPlayerFactory(contextProvider);
  }

  public static AudioPlayer provideAudioPlayer(Context context) {
    return Preconditions.checkNotNullFromProvides(AudioModule.INSTANCE.provideAudioPlayer(context));
  }
}
