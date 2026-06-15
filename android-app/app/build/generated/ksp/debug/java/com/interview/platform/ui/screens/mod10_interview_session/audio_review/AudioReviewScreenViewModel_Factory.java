package com.interview.platform.ui.screens.mod10_interview_session.audio_review;

import android.content.Context;
import com.interview.platform.domain.audio.AudioPlayer;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class AudioReviewScreenViewModel_Factory implements Factory<AudioReviewScreenViewModel> {
  private final Provider<AudioPlayer> audioPlayerProvider;

  private final Provider<Context> contextProvider;

  public AudioReviewScreenViewModel_Factory(Provider<AudioPlayer> audioPlayerProvider,
      Provider<Context> contextProvider) {
    this.audioPlayerProvider = audioPlayerProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public AudioReviewScreenViewModel get() {
    return newInstance(audioPlayerProvider.get(), contextProvider.get());
  }

  public static AudioReviewScreenViewModel_Factory create(Provider<AudioPlayer> audioPlayerProvider,
      Provider<Context> contextProvider) {
    return new AudioReviewScreenViewModel_Factory(audioPlayerProvider, contextProvider);
  }

  public static AudioReviewScreenViewModel newInstance(AudioPlayer audioPlayer, Context context) {
    return new AudioReviewScreenViewModel(audioPlayer, context);
  }
}
