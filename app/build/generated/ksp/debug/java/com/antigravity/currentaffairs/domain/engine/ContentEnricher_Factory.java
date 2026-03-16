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
public final class ContentEnricher_Factory implements Factory<ContentEnricher> {
  @Override
  public ContentEnricher get() {
    return newInstance();
  }

  public static ContentEnricher_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ContentEnricher newInstance() {
    return new ContentEnricher();
  }

  private static final class InstanceHolder {
    private static final ContentEnricher_Factory INSTANCE = new ContentEnricher_Factory();
  }
}
