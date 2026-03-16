package com.antigravity.currentaffairs.ui.settings;

import com.antigravity.currentaffairs.data.local.PreferenceManager;
import com.antigravity.currentaffairs.data.repository.NewsRepository;
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
    "KotlinInternalInJava",
    "cast"
})
public final class SettingsViewModel_Factory implements Factory<SettingsViewModel> {
  private final Provider<PreferenceManager> preferenceManagerProvider;

  private final Provider<NewsRepository> newsRepositoryProvider;

  public SettingsViewModel_Factory(Provider<PreferenceManager> preferenceManagerProvider,
      Provider<NewsRepository> newsRepositoryProvider) {
    this.preferenceManagerProvider = preferenceManagerProvider;
    this.newsRepositoryProvider = newsRepositoryProvider;
  }

  @Override
  public SettingsViewModel get() {
    return newInstance(preferenceManagerProvider.get(), newsRepositoryProvider.get());
  }

  public static SettingsViewModel_Factory create(
      Provider<PreferenceManager> preferenceManagerProvider,
      Provider<NewsRepository> newsRepositoryProvider) {
    return new SettingsViewModel_Factory(preferenceManagerProvider, newsRepositoryProvider);
  }

  public static SettingsViewModel newInstance(PreferenceManager preferenceManager,
      NewsRepository newsRepository) {
    return new SettingsViewModel(preferenceManager, newsRepository);
  }
}
