package com.antigravity.currentaffairs.di

import android.content.Context
import androidx.room.Room
import com.antigravity.currentaffairs.data.local.AppDatabase
import com.antigravity.currentaffairs.data.local.dao.BookmarkDao
import com.antigravity.currentaffairs.data.local.dao.CurrentAffairDao
import com.antigravity.currentaffairs.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            Constants.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCurrentAffairDao(database: AppDatabase): CurrentAffairDao {
        return database.currentAffairDao()
    }

    @Provides
    @Singleton
    fun provideBookmarkDao(database: AppDatabase): BookmarkDao {
        return database.bookmarkDao()
    }
}
