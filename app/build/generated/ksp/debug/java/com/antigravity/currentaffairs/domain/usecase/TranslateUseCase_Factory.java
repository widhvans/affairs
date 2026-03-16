package com.antigravity.currentaffairs.domain.usecase;

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
public final class TranslateUseCase_Factory implements Factory<TranslateUseCase> {
  @Override
  public TranslateUseCase get() {
    return newInstance();
  }

  public static TranslateUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static TranslateUseCase newInstance() {
    return new TranslateUseCase();
  }

  private static final class InstanceHolder {
    private static final TranslateUseCase_Factory INSTANCE = new TranslateUseCase_Factory();
  }
}
