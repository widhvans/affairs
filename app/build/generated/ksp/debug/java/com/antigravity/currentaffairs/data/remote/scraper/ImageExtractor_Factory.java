package com.antigravity.currentaffairs.data.remote.scraper;

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
public final class ImageExtractor_Factory implements Factory<ImageExtractor> {
  @Override
  public ImageExtractor get() {
    return newInstance();
  }

  public static ImageExtractor_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ImageExtractor newInstance() {
    return new ImageExtractor();
  }

  private static final class InstanceHolder {
    private static final ImageExtractor_Factory INSTANCE = new ImageExtractor_Factory();
  }
}
