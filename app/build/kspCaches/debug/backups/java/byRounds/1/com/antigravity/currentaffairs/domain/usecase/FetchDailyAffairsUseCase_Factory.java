package com.antigravity.currentaffairs.domain.usecase;

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
public final class FetchDailyAffairsUseCase_Factory implements Factory<FetchDailyAffairsUseCase> {
  private final Provider<NewsRepository> newsRepositoryProvider;

  public FetchDailyAffairsUseCase_Factory(Provider<NewsRepository> newsRepositoryProvider) {
    this.newsRepositoryProvider = newsRepositoryProvider;
  }

  @Override
  public FetchDailyAffairsUseCase get() {
    return newInstance(newsRepositoryProvider.get());
  }

  public static FetchDailyAffairsUseCase_Factory create(
      Provider<NewsRepository> newsRepositoryProvider) {
    return new FetchDailyAffairsUseCase_Factory(newsRepositoryProvider);
  }

  public static FetchDailyAffairsUseCase newInstance(NewsRepository newsRepository) {
    return new FetchDailyAffairsUseCase(newsRepository);
  }
}
