package com.antigravity.currentaffairs.domain.usecase;

import com.antigravity.currentaffairs.domain.engine.ContentFilter;
import com.antigravity.currentaffairs.domain.engine.RelevanceScorer;
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
public final class FilterAndRankUseCase_Factory implements Factory<FilterAndRankUseCase> {
  private final Provider<ContentFilter> contentFilterProvider;

  private final Provider<RelevanceScorer> relevanceScorerProvider;

  public FilterAndRankUseCase_Factory(Provider<ContentFilter> contentFilterProvider,
      Provider<RelevanceScorer> relevanceScorerProvider) {
    this.contentFilterProvider = contentFilterProvider;
    this.relevanceScorerProvider = relevanceScorerProvider;
  }

  @Override
  public FilterAndRankUseCase get() {
    return newInstance(contentFilterProvider.get(), relevanceScorerProvider.get());
  }

  public static FilterAndRankUseCase_Factory create(Provider<ContentFilter> contentFilterProvider,
      Provider<RelevanceScorer> relevanceScorerProvider) {
    return new FilterAndRankUseCase_Factory(contentFilterProvider, relevanceScorerProvider);
  }

  public static FilterAndRankUseCase newInstance(ContentFilter contentFilter,
      RelevanceScorer relevanceScorer) {
    return new FilterAndRankUseCase(contentFilter, relevanceScorer);
  }
}
