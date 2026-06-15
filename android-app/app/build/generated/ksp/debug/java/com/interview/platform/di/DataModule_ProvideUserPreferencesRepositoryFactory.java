package com.interview.platform.di;

import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.Preferences;
import com.interview.platform.data.repository.UserPreferencesRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class DataModule_ProvideUserPreferencesRepositoryFactory implements Factory<UserPreferencesRepository> {
  private final Provider<DataStore<Preferences>> dataStoreProvider;

  public DataModule_ProvideUserPreferencesRepositoryFactory(
      Provider<DataStore<Preferences>> dataStoreProvider) {
    this.dataStoreProvider = dataStoreProvider;
  }

  @Override
  public UserPreferencesRepository get() {
    return provideUserPreferencesRepository(dataStoreProvider.get());
  }

  public static DataModule_ProvideUserPreferencesRepositoryFactory create(
      Provider<DataStore<Preferences>> dataStoreProvider) {
    return new DataModule_ProvideUserPreferencesRepositoryFactory(dataStoreProvider);
  }

  public static UserPreferencesRepository provideUserPreferencesRepository(
      DataStore<Preferences> dataStore) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideUserPreferencesRepository(dataStore));
  }
}
