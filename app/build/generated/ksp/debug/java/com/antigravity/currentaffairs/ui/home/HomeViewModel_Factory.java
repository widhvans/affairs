package com.antigravity.currentaffairs.ui.home;

import com.antigravity.currentaffairs.data.local.PreferenceManager;
import com.antigravity.currentaffairs.domain.usecase.BookmarkUseCase;
import com.antigravity.currentaffairs.domain.usecase.FetchDailyAffairsUseCase;
import com.antigravity.currentaffairs.domain.usecase.GetAffairsByDateUseCase;
import com.antigravity.currentaffairs.utils.NetworkUtils;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<FetchDailyAffairsUseCase> fetchDailyAffairsUseCaseProvider;

  private final Provider<GetAffairsByDateUseCase> getAffairsByDateUseCaseProvider;

  private final Provider<BookmarkUseCase> bookmarkUseCaseProvider;

  private final Provider<PreferenceManager> preferenceManagerProvider;

  private final Provider<NetworkUtils> networkUtilsProvider;

  public HomeViewModel_Factory(Provider<FetchDailyAffairsUseCase> fetchDailyAffairsUseCaseProvider,
      Provider<GetAffairsByDateUseCase> getAffairsByDateUseCaseProvider,
      Provider<BookmarkUseCase> bookmarkUseCaseProvider,
      Provider<PreferenceManager> preferenceManagerProvider,
      Provider<NetworkUtils> networkUtilsProvider) {
    this.fetchDailyAffairsUseCaseProvider = fetchDailyAffairsUseCaseProvider;
    this.getAffairsByDateUseCaseProvider = getAffairsByDateUseCaseProvider;
    this.bookmarkUseCaseProvider = bookmarkUseCaseProvider;
    this.preferenceManagerProvider = preferenceManagerProvider;
    this.networkUtilsProvider = networkUtilsProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(fetchDailyAffairsUseCaseProvider.get(), getAffairsByDateUseCaseProvider.get(), bookmarkUseCaseProvider.get(), preferenceManagerProvider.get(), networkUtilsProvider.get());
  }

  public static HomeViewModel_Factory create(
      Provider<FetchDailyAffairsUseCase> fetchDailyAffairsUseCaseProvider,
      Provider<GetAffairsByDateUseCase> getAffairsByDateUseCaseProvider,
      Provider<BookmarkUseCase> bookmarkUseCaseProvider,
      Provider<PreferenceManager> preferenceManagerProvider,
      Provider<NetworkUtils> networkUtilsProvider) {
    return new HomeViewModel_Factory(fetchDailyAffairsUseCaseProvider, getAffairsByDateUseCaseProvider, bookmarkUseCaseProvider, preferenceManagerProvider, networkUtilsProvider);
  }

  public static HomeViewModel newInstance(FetchDailyAffairsUseCase fetchDailyAffairsUseCase,
      GetAffairsByDateUseCase getAffairsByDateUseCase, BookmarkUseCase bookmarkUseCase,
      PreferenceManager preferenceManager, NetworkUtils networkUtils) {
    return new HomeViewModel(fetchDailyAffairsUseCase, getAffairsByDateUseCase, bookmarkUseCase, preferenceManager, networkUtils);
  }
}
