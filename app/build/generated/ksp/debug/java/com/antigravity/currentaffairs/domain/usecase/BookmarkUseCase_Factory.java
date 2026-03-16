package com.antigravity.currentaffairs.domain.usecase;

import com.antigravity.currentaffairs.data.repository.BookmarkRepository;
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
public final class BookmarkUseCase_Factory implements Factory<BookmarkUseCase> {
  private final Provider<BookmarkRepository> bookmarkRepositoryProvider;

  private final Provider<NewsRepository> newsRepositoryProvider;

  public BookmarkUseCase_Factory(Provider<BookmarkRepository> bookmarkRepositoryProvider,
      Provider<NewsRepository> newsRepositoryProvider) {
    this.bookmarkRepositoryProvider = bookmarkRepositoryProvider;
    this.newsRepositoryProvider = newsRepositoryProvider;
  }

  @Override
  public BookmarkUseCase get() {
    return newInstance(bookmarkRepositoryProvider.get(), newsRepositoryProvider.get());
  }

  public static BookmarkUseCase_Factory create(
      Provider<BookmarkRepository> bookmarkRepositoryProvider,
      Provider<NewsRepository> newsRepositoryProvider) {
    return new BookmarkUseCase_Factory(bookmarkRepositoryProvider, newsRepositoryProvider);
  }

  public static BookmarkUseCase newInstance(BookmarkRepository bookmarkRepository,
      NewsRepository newsRepository) {
    return new BookmarkUseCase(bookmarkRepository, newsRepository);
  }
}
