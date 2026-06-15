package com.interview.platform.data.repository;

import com.interview.platform.data.local.dao.CandidateProfileDao;
import com.interview.platform.data.remote.api.Mod02UserProfileApiService;
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
public final class ProfileRepositoryImpl_Factory implements Factory<ProfileRepositoryImpl> {
  private final Provider<Mod02UserProfileApiService> apiServiceProvider;

  private final Provider<CandidateProfileDao> daoProvider;

  public ProfileRepositoryImpl_Factory(Provider<Mod02UserProfileApiService> apiServiceProvider,
      Provider<CandidateProfileDao> daoProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.daoProvider = daoProvider;
  }

  @Override
  public ProfileRepositoryImpl get() {
    return newInstance(apiServiceProvider.get(), daoProvider.get());
  }

  public static ProfileRepositoryImpl_Factory create(
      Provider<Mod02UserProfileApiService> apiServiceProvider,
      Provider<CandidateProfileDao> daoProvider) {
    return new ProfileRepositoryImpl_Factory(apiServiceProvider, daoProvider);
  }

  public static ProfileRepositoryImpl newInstance(Mod02UserProfileApiService apiService,
      CandidateProfileDao dao) {
    return new ProfileRepositoryImpl(apiService, dao);
  }
}
