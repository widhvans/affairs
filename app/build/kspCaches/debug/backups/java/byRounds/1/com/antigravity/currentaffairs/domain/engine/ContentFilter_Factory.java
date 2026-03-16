package com.antigravity.currentaffairs.domain.engine;

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
public final class ContentFilter_Factory implements Factory<ContentFilter> {
  private final Provider<RelevanceScorer> relevanceScorerProvider;

  public ContentFilter_Factory(Provider<RelevanceScorer> relevanceScorerProvider) {
    this.relevanceScorerProvider = relevanceScorerProvider;
  }

  @Override
  public ContentFilter get() {
    return newInstance(relevanceScorerProvider.get());
  }

  public static ContentFilter_Factory create(Provider<RelevanceScorer> relevanceScorerProvider) {
    return new ContentFilter_Factory(relevanceScorerProvider);
  }

  public static ContentFilter newInstance(RelevanceScorer relevanceScorer) {
    return new ContentFilter(relevanceScorer);
  }
}
