package com.antigravity.currentaffairs.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.antigravity.currentaffairs.data.local.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {

    @Query("SELECT * FROM bookmarks ORDER BY bookmarkedAt DESC")
    fun getAllBookmarks(): Flow<List<BookmarkEntity>>

    @Query("SELECT * FROM bookmarks WHERE date = :date ORDER BY bookmarkedAt DESC")
    fun getBookmarksByDate(date: String): Flow<List<BookmarkEntity>>

    @Query("SELECT * FROM bookmarks WHERE affairId = :affairId")
    suspend fun getBookmarkById(affairId: String): BookmarkEntity?

    @Query("SELECT * FROM bookmarks WHERE titleEnglish LIKE '%' || :query || '%' OR titleHindi LIKE '%' || :query || '%' ORDER BY bookmarkedAt DESC")
    fun searchBookmarks(query: String): Flow<List<BookmarkEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bookmark: BookmarkEntity)

    @Query("DELETE FROM bookmarks WHERE affairId = :affairId")
    suspend fun deleteById(affairId: String)

    @Delete
    suspend fun delete(bookmark: BookmarkEntity)

    @Query("SELECT COUNT(*) FROM bookmarks")
    suspend fun getBookmarkCount(): Int

    @Query("SELECT DISTINCT date FROM bookmarks ORDER BY date DESC")
    fun getAllBookmarkDates(): Flow<List<String>>
}
