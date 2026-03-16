package com.antigravity.currentaffairs.data.repository;

import com.antigravity.currentaffairs.data.local.PreferenceManager;
import com.antigravity.currentaffairs.data.local.dao.CurrentAffairDao;
import com.antigravity.currentaffairs.data.remote.api.CurrentsApiService;
import com.antigravity.currentaffairs.data.remote.api.GNewsApiService;
import com.antigravity.currentaffairs.data.remote.rss.RssFeedParser;
import com.antigravity.currentaffairs.domain.engine.ContentEnricher;
import com.antigravity.currentaffairs.domain.engine.ContentFilter;
import com.antigravity.currentaffairs.domain.engine.RelevanceScorer;
import com.antigravity.currentaffairs.domain.engine.SimilarityChecker;
import com.antigravity.currentaffairs.domain.usecase.TranslateUseCase;
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
public final class NewsRepositoryImpl_Factory implements Factory<NewsRepositoryImpl> {
  private final Provider<CurrentAffairDao> currentAffairDaoProvider;

  private final Provider<RssFeedParser> rssFeedParserProvider;

  private final Provider<GNewsApiService> gNewsApiServiceProvider;

  private final Provider<CurrentsApiService> currentsApiServiceProvider;

  private final Provider<RelevanceScorer> relevanceScorerProvider;

  private final Provider<ContentFilter> contentFilterProvider;

  private final Provider<SimilarityChecker> similarityCheckerProvider;

  private final Provider<ContentEnricher> contentEnricherProvider;

  private final Provider<TranslateUseCase> translateUseCaseProvider;

  private final Provider<PreferenceManager> preferenceManagerProvider;

  private final Provider<Gson> gsonProvider;

  public NewsRepositoryImpl_Factory(Provider<CurrentAffairDao> currentAffairDaoProvider,
      Provider<RssFeedParser> rssFeedParserProvider,
      Provider<GNewsApiService> gNewsApiServiceProvider,
      Provider<CurrentsApiService> currentsApiServiceProvider,
      Provider<RelevanceScorer> relevanceScorerProvider,
      Provider<ContentFilter> contentFilterProvider,
      Provider<SimilarityChecker> similarityCheckerProvider,
      Provider<ContentEnricher> contentEnricherProvider,
      Provider<TranslateUseCase> translateUseCaseProvider,
      Provider<PreferenceManager> preferenceManagerProvider, Provider<Gson> gsonProvider) {
    this.currentAffairDaoProvider = currentAffairDaoProvider;
    this.rssFeedParserProvider = rssFeedParserProvider;
    this.gNewsApiServiceProvider = gNewsApiServiceProvider;
    this.currentsApiServiceProvider = currentsApiServiceProvider;
    this.relevanceScorerProvider = relevanceScorerProvider;
    this.contentFilterProvider = contentFilterProvider;
    this.similarityCheckerProvider = similarityCheckerProvider;
    this.contentEnricherProvider = contentEnricherProvider;
    this.translateUseCaseProvider = translateUseCaseProvider;
    this.preferenceManagerProvider = preferenceManagerProvider;
    this.gsonProvider = gsonProvider;
  }

  @Override
  public NewsRepositoryImpl get() {
    return newInstance(currentAffairDaoProvider.get(), rssFeedParserProvider.get(), gNewsApiServiceProvider.get(), currentsApiServiceProvider.get(), relevanceScorerProvider.get(), contentFilterProvider.get(), similarityCheckerProvider.get(), contentEnricherProvider.get(), translateUseCaseProvider.get(), preferenceManagerProvider.get(), gsonProvider.get());
  }

  public static NewsRepositoryImpl_Factory create(
      Provider<CurrentAffairDao> currentAffairDaoProvider,
      Provider<RssFeedParser> rssFeedParserProvider,
      Provider<GNewsApiService> gNewsApiServiceProvider,
      Provider<CurrentsApiService> currentsApiServiceProvider,
      Provider<RelevanceScorer> relevanceScorerProvider,
      Provider<ContentFilter> contentFilterProvider,
      Provider<SimilarityChecker> similarityCheckerProvider,
      Provider<ContentEnricher> contentEnricherProvider,
      Provider<TranslateUseCase> translateUseCaseProvider,
      Provider<PreferenceManager> preferenceManagerProvider, Provider<Gson> gsonProvider) {
    return new NewsRepositoryImpl_Factory(currentAffairDaoProvider, rssFeedParserProvider, gNewsApiServiceProvider, currentsApiServiceProvider, relevanceScorerProvider, contentFilterProvider, similarityCheckerProvider, contentEnricherProvider, translateUseCaseProvider, preferenceManagerProvider, gsonProvider);
  }

  public static NewsRepositoryImpl newInstance(CurrentAffairDao currentAffairDao,
      RssFeedParser rssFeedParser, GNewsApiService gNewsApiService,
      CurrentsApiService currentsApiService, RelevanceScorer relevanceScorer,
      ContentFilter contentFilter, SimilarityChecker similarityChecker,
      ContentEnricher contentEnricher, TranslateUseCase translateUseCase,
      PreferenceManager preferenceManager, Gson gson) {
    return new NewsRepositoryImpl(currentAffairDao, rssFeedParser, gNewsApiService, currentsApiService, relevanceScorer, contentFilter, similarityChecker, contentEnricher, translateUseCase, preferenceManager, gson);
  }
}
