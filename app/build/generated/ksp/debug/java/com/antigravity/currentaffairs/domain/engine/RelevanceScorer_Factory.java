package com.antigravity.currentaffairs.domain.engine;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class RelevanceScorer_Factory implements Factory<RelevanceScorer> {
  @Override
  public RelevanceScorer get() {
    return newInstance();
  }

  public static RelevanceScorer_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static RelevanceScorer newInstance() {
    return new RelevanceScorer();
  }

  private static final class InstanceHolder {
    private static final RelevanceScorer_Factory INSTANCE = new RelevanceScorer_Factory();
  }
}
