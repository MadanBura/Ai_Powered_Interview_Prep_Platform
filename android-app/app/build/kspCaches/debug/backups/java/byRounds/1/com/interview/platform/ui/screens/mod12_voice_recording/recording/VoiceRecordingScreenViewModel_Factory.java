package com.interview.platform.ui.screens.mod12_voice_recording.recording;

import android.content.Context;
import com.interview.platform.domain.audio.AudioRecorder;
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
public final class VoiceRecordingScreenViewModel_Factory implements Factory<VoiceRecordingScreenViewModel> {
  private final Provider<AudioRecorder> audioRecorderProvider;

  private final Provider<Context> contextProvider;

  public VoiceRecordingScreenViewModel_Factory(Provider<AudioRecorder> audioRecorderProvider,
      Provider<Context> contextProvider) {
    this.audioRecorderProvider = audioRecorderProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public VoiceRecordingScreenViewModel get() {
    return newInstance(audioRecorderProvider.get(), contextProvider.get());
  }

  public static VoiceRecordingScreenViewModel_Factory create(
      Provider<AudioRecorder> audioRecorderProvider, Provider<Context> contextProvider) {
    return new VoiceRecordingScreenViewModel_Factory(audioRecorderProvider, contextProvider);
  }

  public static VoiceRecordingScreenViewModel newInstance(AudioRecorder audioRecorder,
      Context context) {
    return new VoiceRecordingScreenViewModel(audioRecorder, context);
  }
}
