package com.antigravity.currentaffairs.di;

import android.content.Context;
import com.antigravity.currentaffairs.utils.NetworkUtils;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class AppModule_ProvideNetworkUtilsFactory implements Factory<NetworkUtils> {
  private final Provider<Context> contextProvider;

  public AppModule_ProvideNetworkUtilsFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public NetworkUtils get() {
    return provideNetworkUtils(contextProvider.get());
  }

  public static AppModule_ProvideNetworkUtilsFactory create(Provider<Context> contextProvider) {
    return new AppModule_ProvideNetworkUtilsFactory(contextProvider);
  }

  public static NetworkUtils provideNetworkUtils(Context context) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideNetworkUtils(context));
  }
}
