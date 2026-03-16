package com.antigravity.currentaffairs.worker;

import androidx.hilt.work.WorkerAssistedFactory;
import androidx.work.ListenableWorker;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import javax.annotation.processing.Generated;

@Generated("androidx.hilt.AndroidXHiltProcessor")
@Module
@InstallIn(SingletonComponent.class)
@OriginatingElement(
    topLevelClass = DailyFetchWorker.class
)
public interface DailyFetchWorker_HiltModule {
  @Binds
  @IntoMap
  @StringKey("com.antigravity.currentaffairs.worker.DailyFetchWorker")
  WorkerAssistedFactory<? extends ListenableWorker> bind(DailyFetchWorker_AssistedFactory factory);
}
