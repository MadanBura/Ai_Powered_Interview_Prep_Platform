package com.interview.platform.ui.screens.mod11_question_delivery.question_submitted;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class QuestionSubmittedScreenViewModel_Factory implements Factory<QuestionSubmittedScreenViewModel> {
  @Override
  public QuestionSubmittedScreenViewModel get() {
    return newInstance();
  }

  public static QuestionSubmittedScreenViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static QuestionSubmittedScreenViewModel newInstance() {
    return new QuestionSubmittedScreenViewModel();
  }

  private static final class InstanceHolder {
    private static final QuestionSubmittedScreenViewModel_Factory INSTANCE = new QuestionSubmittedScreenViewModel_Factory();
  }
}
