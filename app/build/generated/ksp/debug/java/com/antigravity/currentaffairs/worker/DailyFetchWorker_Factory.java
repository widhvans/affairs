package com.antigravity.currentaffairs.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.antigravity.currentaffairs.data.repository.NewsRepository;
import dagger.internal.DaggerGenerated;
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
public final class DailyFetchWorker_Factory {
  private final Provider<NewsRepository> newsRepositoryProvider;

  public DailyFetchWorker_Factory(Provider<NewsRepository> newsRepositoryProvider) {
    this.newsRepositoryProvider = newsRepositoryProvider;
  }

  public DailyFetchWorker get(Context context, WorkerParameters params) {
    return newInstance(context, params, newsRepositoryProvider.get());
  }

  public static DailyFetchWorker_Factory create(Provider<NewsRepository> newsRepositoryProvider) {
    return new DailyFetchWorker_Factory(newsRepositoryProvider);
  }

  public static DailyFetchWorker newInstance(Context context, WorkerParameters params,
      NewsRepository newsRepository) {
    return new DailyFetchWorker(context, params, newsRepository);
  }
}
