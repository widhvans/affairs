package com.antigravity.currentaffairs.di;

import com.antigravity.currentaffairs.data.remote.api.GNewsApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("javax.inject.Named")
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
public final class NetworkModule_ProvideGNewsApiServiceFactory implements Factory<GNewsApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideGNewsApiServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public GNewsApiService get() {
    return provideGNewsApiService(retrofitProvider.get());
  }

  public static NetworkModule_ProvideGNewsApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideGNewsApiServiceFactory(retrofitProvider);
  }

  public static GNewsApiService provideGNewsApiService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideGNewsApiService(retrofit));
  }
}
