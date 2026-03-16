package com.antigravity.currentaffairs.data.remote.rss;

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
public final class RssFeedParser_Factory implements Factory<RssFeedParser> {
  @Override
  public RssFeedParser get() {
    return newInstance();
  }

  public static RssFeedParser_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static RssFeedParser newInstance() {
    return new RssFeedParser();
  }

  private static final class InstanceHolder {
    private static final RssFeedParser_Factory INSTANCE = new RssFeedParser_Factory();
  }
}
