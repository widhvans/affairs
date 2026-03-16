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
public final class WebScraper_Factory implements Factory<WebScraper> {
  @Override
  public WebScraper get() {
    return newInstance();
  }

  public static WebScraper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static WebScraper newInstance() {
    return new WebScraper();
  }

  private static final class InstanceHolder {
    private static final WebScraper_Factory INSTANCE = new WebScraper_Factory();
  }
}
