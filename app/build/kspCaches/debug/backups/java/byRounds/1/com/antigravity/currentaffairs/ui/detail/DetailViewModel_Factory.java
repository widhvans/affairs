package com.antigravity.currentaffairs.ui.detail;

import com.antigravity.currentaffairs.data.local.PreferenceManager;
import com.antigravity.currentaffairs.domain.usecase.BookmarkUseCase;
import com.antigravity.currentaffairs.domain.usecase.GetAffairsByDateUseCase;
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
public final class DetailViewModel_Factory implements Factory<DetailViewModel> {
  private final Provider<GetAffairsByDateUseCase> getAffairsByDateUseCaseProvider;

  private final Provider<BookmarkUseCase> bookmarkUseCaseProvider;

  private final Provider<PreferenceManager> preferenceManagerProvider;

  public DetailViewModel_Factory(Provider<GetAffairsByDateUseCase> getAffairsByDateUseCaseProvider,
      Provider<BookmarkUseCase> bookmarkUseCaseProvider,
      Provider<PreferenceManager> preferenceManagerProvider) {
    this.getAffairsByDateUseCaseProvider = getAffairsByDateUseCaseProvider;
    this.bookmarkUseCaseProvider = bookmarkUseCaseProvider;
    this.preferenceManagerProvider = preferenceManagerProvider;
  }

  @Override
  public DetailViewModel get() {
    return newInstance(getAffairsByDateUseCaseProvider.get(), bookmarkUseCaseProvider.get(), preferenceManagerProvider.get());
  }

  public static DetailViewModel_Factory create(
      Provider<GetAffairsByDateUseCase> getAffairsByDateUseCaseProvider,
      Provider<BookmarkUseCase> bookmarkUseCaseProvider,
      Provider<PreferenceManager> preferenceManagerProvider) {
    return new DetailViewModel_Factory(getAffairsByDateUseCaseProvider, bookmarkUseCaseProvider, preferenceManagerProvider);
  }

  public static DetailViewModel newInstance(GetAffairsByDateUseCase getAffairsByDateUseCase,
      BookmarkUseCase bookmarkUseCase, PreferenceManager preferenceManager) {
    return new DetailViewModel(getAffairsByDateUseCase, bookmarkUseCase, preferenceManager);
  }
}
