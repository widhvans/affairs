package com.antigravity.currentaffairs.di;

import com.antigravity.currentaffairs.data.remote.api.CurrentsApiService;
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
public final class NetworkModule_ProvideCurrentsApiServiceFactory implements Factory<CurrentsApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideCurrentsApiServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public CurrentsApiService get() {
    return provideCurrentsApiService(retrofitProvider.get());
  }

  public static NetworkModule_ProvideCurrentsApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideCurrentsApiServiceFactory(retrofitProvider);
  }

  public static CurrentsApiService provideCurrentsApiService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideCurrentsApiService(retrofit));
  }
}
