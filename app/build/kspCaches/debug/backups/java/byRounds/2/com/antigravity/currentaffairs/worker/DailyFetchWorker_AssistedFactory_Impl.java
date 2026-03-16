package com.antigravity.currentaffairs.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class DailyFetchWorker_AssistedFactory_Impl implements DailyFetchWorker_AssistedFactory {
  private final DailyFetchWorker_Factory delegateFactory;

  DailyFetchWorker_AssistedFactory_Impl(DailyFetchWorker_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public DailyFetchWorker create(Context p0, WorkerParameters p1) {
    return delegateFactory.get(p0, p1);
  }

  public static Provider<DailyFetchWorker_AssistedFactory> create(
      DailyFetchWorker_Factory delegateFactory) {
    return InstanceFactory.create(new DailyFetchWorker_AssistedFactory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<DailyFetchWorker_AssistedFactory> createFactoryProvider(
      DailyFetchWorker_Factory delegateFactory) {
    return InstanceFactory.create(new DailyFetchWorker_AssistedFactory_Impl(delegateFactory));
  }
}
