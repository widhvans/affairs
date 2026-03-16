package com.antigravity.currentaffairs.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.antigravity.currentaffairs.data.repository.NewsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class DailyFetchWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted params: WorkerParameters,
    private val newsRepository: NewsRepository
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return try {
            // Fetch and store daily affairs
            val affairs = newsRepository.fetchAndStoreDailyAffairs()

            if (affairs.isNotEmpty()) {
                NotificationHelper.showDailyNotification(
                    applicationContext,
                    "Today's ${affairs.size} Current Affairs are ready! Tap to read."
                )
            }

            // Cleanup old data
            newsRepository.cleanupOldData(30)

            Result.success()
        } catch (e: Exception) {
            if (runAttemptCount < 3) {
                Result.retry()
            } else {
                Result.failure()
            }
        }
    }
}
