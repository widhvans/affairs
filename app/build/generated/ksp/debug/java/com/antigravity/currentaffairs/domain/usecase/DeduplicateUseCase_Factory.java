package com.antigravity.currentaffairs.domain.usecase;

import com.antigravity.currentaffairs.domain.engine.SimilarityChecker;
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
public final class DeduplicateUseCase_Factory implements Factory<DeduplicateUseCase> {
  private final Provider<SimilarityChecker> similarityCheckerProvider;

  public DeduplicateUseCase_Factory(Provider<SimilarityChecker> similarityCheckerProvider) {
    this.similarityCheckerProvider = similarityCheckerProvider;
  }

  @Override
  public DeduplicateUseCase get() {
    return newInstance(similarityCheckerProvider.get());
  }

  public static DeduplicateUseCase_Factory create(
      Provider<SimilarityChecker> similarityCheckerProvider) {
    return new DeduplicateUseCase_Factory(similarityCheckerProvider);
  }

  public static DeduplicateUseCase newInstance(SimilarityChecker similarityChecker) {
    return new DeduplicateUseCase(similarityChecker);
  }
}
