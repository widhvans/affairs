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
public final class SimilarityChecker_Factory implements Factory<SimilarityChecker> {
  @Override
  public SimilarityChecker get() {
    return newInstance();
  }

  public static SimilarityChecker_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SimilarityChecker newInstance() {
    return new SimilarityChecker();
  }

  private static final class InstanceHolder {
    private static final SimilarityChecker_Factory INSTANCE = new SimilarityChecker_Factory();
  }
}
