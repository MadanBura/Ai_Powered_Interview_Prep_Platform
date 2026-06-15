package com.interview.platform.ui.screens.mod13_speech_to_text.speech_to_text;

import com.interview.platform.data.remote.api.Mod10InterviewSessionApiService;
import com.interview.platform.data.repository.InterviewSessionManager;
import com.interview.platform.domain.audio.SpeechRecognizerService;
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
public final class SpeechToTextScreenViewModel_Factory implements Factory<SpeechToTextScreenViewModel> {
  private final Provider<SpeechRecognizerService> speechRecognizerProvider;

  private final Provider<Mod10InterviewSessionApiService> sessionApiProvider;

  private final Provider<InterviewSessionManager> sessionManagerProvider;

  public SpeechToTextScreenViewModel_Factory(
      Provider<SpeechRecognizerService> speechRecognizerProvider,
      Provider<Mod10InterviewSessionApiService> sessionApiProvider,
      Provider<InterviewSessionManager> sessionManagerProvider) {
    this.speechRecognizerProvider = speechRecognizerProvider;
    this.sessionApiProvider = sessionApiProvider;
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public SpeechToTextScreenViewModel get() {
    return newInstance(speechRecognizerProvider.get(), sessionApiProvider.get(), sessionManagerProvider.get());
  }

  public static SpeechToTextScreenViewModel_Factory create(
      Provider<SpeechRecognizerService> speechRecognizerProvider,
      Provider<Mod10InterviewSessionApiService> sessionApiProvider,
      Provider<InterviewSessionManager> sessionManagerProvider) {
    return new SpeechToTextScreenViewModel_Factory(speechRecognizerProvider, sessionApiProvider, sessionManagerProvider);
  }

  public static SpeechToTextScreenViewModel newInstance(SpeechRecognizerService speechRecognizer,
      Mod10InterviewSessionApiService sessionApi, InterviewSessionManager sessionManager) {
    return new SpeechToTextScreenViewModel(speechRecognizer, sessionApi, sessionManager);
  }
}
