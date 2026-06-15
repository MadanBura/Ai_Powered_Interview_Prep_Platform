package com.interview.platform.di.mod11_question_delivery;

import com.interview.platform.data.remote.api.Mod11QuestionDeliveryApiService;
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
public final class Mod11QuestionDeliveryApiModule_ProvideMod11QuestionDeliveryApiServiceFactory implements Factory<Mod11QuestionDeliveryApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public Mod11QuestionDeliveryApiModule_ProvideMod11QuestionDeliveryApiServiceFactory(
      Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public Mod11QuestionDeliveryApiService get() {
    return provideMod11QuestionDeliveryApiService(retrofitProvider.get());
  }

  public static Mod11QuestionDeliveryApiModule_ProvideMod11QuestionDeliveryApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new Mod11QuestionDeliveryApiModule_ProvideMod11QuestionDeliveryApiServiceFactory(retrofitProvider);
  }

  public static Mod11QuestionDeliveryApiService provideMod11QuestionDeliveryApiService(
      Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(Mod11QuestionDeliveryApiModule.INSTANCE.provideMod11QuestionDeliveryApiService(retrofit));
  }
}
