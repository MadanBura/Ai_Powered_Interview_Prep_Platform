package com.interview.platform.data.repository;

import com.interview.platform.data.local.dao.DepartmentDao;
import com.interview.platform.data.remote.api.Mod03DepartmentManagementApiService;
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
public final class DepartmentRepositoryImpl_Factory implements Factory<DepartmentRepositoryImpl> {
  private final Provider<Mod03DepartmentManagementApiService> apiServiceProvider;

  private final Provider<DepartmentDao> daoProvider;

  public DepartmentRepositoryImpl_Factory(
      Provider<Mod03DepartmentManagementApiService> apiServiceProvider,
      Provider<DepartmentDao> daoProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.daoProvider = daoProvider;
  }

  @Override
  public DepartmentRepositoryImpl get() {
    return newInstance(apiServiceProvider.get(), daoProvider.get());
  }

  public static DepartmentRepositoryImpl_Factory create(
      Provider<Mod03DepartmentManagementApiService> apiServiceProvider,
      Provider<DepartmentDao> daoProvider) {
    return new DepartmentRepositoryImpl_Factory(apiServiceProvider, daoProvider);
  }

  public static DepartmentRepositoryImpl newInstance(Mod03DepartmentManagementApiService apiService,
      DepartmentDao dao) {
    return new DepartmentRepositoryImpl(apiService, dao);
  }
}
