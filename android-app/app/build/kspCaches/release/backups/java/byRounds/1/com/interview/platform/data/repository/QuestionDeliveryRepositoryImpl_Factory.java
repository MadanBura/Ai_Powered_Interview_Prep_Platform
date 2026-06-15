package com.interview.platform.data.repository;

import com.interview.platform.data.local.dao.QuestionDeliveryDao;
import com.interview.platform.data.remote.api.Mod11QuestionDeliveryApiService;
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
public final class QuestionDeliveryRepositoryImpl_Factory implements Factory<QuestionDeliveryRepositoryImpl> {
  private final Provider<Mod11QuestionDeliveryApiService> apiServiceProvider;

  private final Provider<QuestionDeliveryDao> daoProvider;

  public QuestionDeliveryRepositoryImpl_Factory(
      Provider<Mod11QuestionDeliveryApiService> apiServiceProvider,
      Provider<QuestionDeliveryDao> daoProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.daoProvider = daoProvider;
  }

  @Override
  public QuestionDeliveryRepositoryImpl get() {
    return newInstance(apiServiceProvider.get(), daoProvider.get());
  }

  public static QuestionDeliveryRepositoryImpl_Factory create(
      Provider<Mod11QuestionDeliveryApiService> apiServiceProvider,
      Provider<QuestionDeliveryDao> daoProvider) {
    return new QuestionDeliveryRepositoryImpl_Factory(apiServiceProvider, daoProvider);
  }

  public static QuestionDeliveryRepositoryImpl newInstance(
      Mod11QuestionDeliveryApiService apiService, QuestionDeliveryDao dao) {
    return new QuestionDeliveryRepositoryImpl(apiService, dao);
  }
}
