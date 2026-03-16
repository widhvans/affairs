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
public final class GetAffairsByDateUseCase_Factory implements Factory<GetAffairsByDateUseCase> {
  private final Provider<NewsRepository> newsRepositoryProvider;

  public GetAffairsByDateUseCase_Factory(Provider<NewsRepository> newsRepositoryProvider) {
    this.newsRepositoryProvider = newsRepositoryProvider;
  }

  @Override
  public GetAffairsByDateUseCase get() {
    return newInstance(newsRepositoryProvider.get());
  }

  public static GetAffairsByDateUseCase_Factory create(
      Provider<NewsRepository> newsRepositoryProvider) {
    return new GetAffairsByDateUseCase_Factory(newsRepositoryProvider);
  }

  public static GetAffairsByDateUseCase newInstance(NewsRepository newsRepository) {
    return new GetAffairsByDateUseCase(newsRepository);
  }
}
