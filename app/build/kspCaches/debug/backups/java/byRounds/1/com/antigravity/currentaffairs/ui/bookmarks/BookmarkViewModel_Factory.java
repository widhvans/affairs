package com.antigravity.currentaffairs.ui.bookmarks;

import com.antigravity.currentaffairs.data.local.PreferenceManager;
import com.antigravity.currentaffairs.domain.usecase.BookmarkUseCase;
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
public final class BookmarkViewModel_Factory implements Factory<BookmarkViewModel> {
  private final Provider<BookmarkUseCase> bookmarkUseCaseProvider;

  private final Provider<PreferenceManager> preferenceManagerProvider;

  public BookmarkViewModel_Factory(Provider<BookmarkUseCase> bookmarkUseCaseProvider,
      Provider<PreferenceManager> preferenceManagerProvider) {
    this.bookmarkUseCaseProvider = bookmarkUseCaseProvider;
    this.preferenceManagerProvider = preferenceManagerProvider;
  }

  @Override
  public BookmarkViewModel get() {
    return newInstance(bookmarkUseCaseProvider.get(), preferenceManagerProvider.get());
  }

  public static BookmarkViewModel_Factory create(Provider<BookmarkUseCase> bookmarkUseCaseProvider,
      Provider<PreferenceManager> preferenceManagerProvider) {
    return new BookmarkViewModel_Factory(bookmarkUseCaseProvider, preferenceManagerProvider);
  }

  public static BookmarkViewModel newInstance(BookmarkUseCase bookmarkUseCase,
      PreferenceManager preferenceManager) {
    return new BookmarkViewModel(bookmarkUseCase, preferenceManager);
  }
}
