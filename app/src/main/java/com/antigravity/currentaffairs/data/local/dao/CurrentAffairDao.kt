package com.antigravity.currentaffairs.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.antigravity.currentaffairs.data.local.entity.CurrentAffairEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentAffairDao {

    @Query("SELECT * FROM current_affairs WHERE date = :date ORDER BY relevanceScore DESC")
    fun getAffairsByDate(date: String): Flow<List<CurrentAffairEntity>>

    @Query("SELECT * FROM current_affairs WHERE date = :date AND category = :category ORDER BY relevanceScore DESC")
    fun getAffairsByDateAndCategory(date: String, category: String): Flow<List<CurrentAffairEntity>>

    @Query("SELECT * FROM current_affairs WHERE id = :id")
    suspend fun getAffairById(id: String): CurrentAffairEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(affairs: List<CurrentAffairEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(affair: CurrentAffairEntity)

    @Update
    suspend fun update(affair: CurrentAffairEntity)

    @Query("UPDATE current_affairs SET isBookmarked = :isBookmarked WHERE id = :id")
    suspend fun updateBookmarkStatus(id: String, isBookmarked: Boolean)

    @Query("SELECT COUNT(*) FROM current_affairs WHERE date = :date")
    suspend fun getAffairsCountByDate(date: String): Int

    @Query("SELECT titleEnglish FROM current_affairs")
    suspend fun getAllTitles(): List<String>

    @Query("DELETE FROM current_affairs WHERE date < :date AND isBookmarked = 0")
    suspend fun deleteOlderThan(date: String)

    @Query("DELETE FROM current_affairs WHERE date = :date")
    suspend fun deleteByDate(date: String)

    @Query("SELECT DISTINCT date FROM current_affairs ORDER BY date DESC")
    suspend fun getAllDates(): List<String>
}
