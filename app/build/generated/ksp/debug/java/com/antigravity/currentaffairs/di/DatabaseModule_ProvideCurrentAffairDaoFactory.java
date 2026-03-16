package com.antigravity.currentaffairs.di;

import com.antigravity.currentaffairs.data.local.AppDatabase;
import com.antigravity.currentaffairs.data.local.dao.CurrentAffairDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class DatabaseModule_ProvideCurrentAffairDaoFactory implements Factory<CurrentAffairDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DatabaseModule_ProvideCurrentAffairDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public CurrentAffairDao get() {
    return provideCurrentAffairDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideCurrentAffairDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvideCurrentAffairDaoFactory(databaseProvider);
  }

  public static CurrentAffairDao provideCurrentAffairDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideCurrentAffairDao(database));
  }
}
