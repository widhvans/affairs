package com.antigravity.currentaffairs.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.antigravity.currentaffairs.data.local.dao.BookmarkDao
import com.antigravity.currentaffairs.data.local.dao.CurrentAffairDao
import com.antigravity.currentaffairs.data.local.entity.BookmarkEntity
import com.antigravity.currentaffairs.data.local.entity.CurrentAffairEntity

@Database(
    entities = [CurrentAffairEntity::class, BookmarkEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currentAffairDao(): CurrentAffairDao
    abstract fun bookmarkDao(): BookmarkDao
}
