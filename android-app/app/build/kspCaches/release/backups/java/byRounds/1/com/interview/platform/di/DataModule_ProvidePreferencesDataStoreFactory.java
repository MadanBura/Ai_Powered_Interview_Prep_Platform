package com.interview.platform.di;

import android.content.Context;
import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.Preferences;
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
public final class DataModule_ProvidePreferencesDataStoreFactory implements Factory<DataStore<Preferences>> {
  private final Provider<Context> appContextProvider;

  public DataModule_ProvidePreferencesDataStoreFactory(Provider<Context> appContextProvider) {
    this.appContextProvider = appContextProvider;
  }

  @Override
  public DataStore<Preferences> get() {
    return providePreferencesDataStore(appContextProvider.get());
  }

  public static DataModule_ProvidePreferencesDataStoreFactory create(
      Provider<Context> appContextProvider) {
    return new DataModule_ProvidePreferencesDataStoreFactory(appContextProvider);
  }

  public static DataStore<Preferences> providePreferencesDataStore(Context appContext) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.providePreferencesDataStore(appContext));
  }
}
