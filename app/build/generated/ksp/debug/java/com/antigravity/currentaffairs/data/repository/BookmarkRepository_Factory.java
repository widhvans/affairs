package com.antigravity.currentaffairs.data.repository;

import com.antigravity.currentaffairs.data.local.dao.BookmarkDao;
import com.google.gson.Gson;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
    "KotlinInternalInJava",
    "cast"
})
public final class BookmarkRepository_Factory implements Factory<BookmarkRepository> {
  private final Provider<BookmarkDao> bookmarkDaoProvider;

  private final Provider<Gson> gsonProvider;

  public BookmarkRepository_Factory(Provider<BookmarkDao> bookmarkDaoProvider,
      Provider<Gson> gsonProvider) {
    this.bookmarkDaoProvider = bookmarkDaoProvider;
    this.gsonProvider = gsonProvider;
  }

  @Override
  public BookmarkRepository get() {
    return newInstance(bookmarkDaoProvider.get(), gsonProvider.get());
  }

  public static BookmarkRepository_Factory create(Provider<BookmarkDao> bookmarkDaoProvider,
      Provider<Gson> gsonProvider) {
    return new BookmarkRepository_Factory(bookmarkDaoProvider, gsonProvider);
  }

  public static BookmarkRepository newInstance(BookmarkDao bookmarkDao, Gson gson) {
    return new BookmarkRepository(bookmarkDao, gson);
  }
}
