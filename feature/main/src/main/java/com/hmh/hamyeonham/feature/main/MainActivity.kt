package com.hmh.hamyeonham.feature.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.main.databinding.ActivityMainBinding
import com.hmh.hamyeonham.feature.main.update.ChallengeWorker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setChallengeWorkerManager()
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.fcvMain.id) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnvMain.setupWithNavController(navController)
    }

    private fun setChallengeWorkerManager() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workerRequest = PeriodicWorkRequestBuilder<ChallengeWorker>(
            repeatInterval = 1,
            repeatIntervalTimeUnit = TimeUnit.DAYS
        )
            .setInitialDelay(calculateInitialDelay(), TimeUnit.MILLISECONDS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "challenge_worker",
            ExistingPeriodicWorkPolicy.KEEP,
            workerRequest
        )
    }

    private fun calculateInitialDelay(): Long {
        val currentTime = Clock.System.now()
        val timeZone = TimeZone.currentSystemDefault()
        val nextMidnight = currentTime.toLocalDateTime(timeZone).date.plus(1, DateTimeUnit.DAY)
            .atStartOfDayIn(timeZone)

        return nextMidnight.toEpochMilliseconds() - currentTime.toEpochMilliseconds()
    }
}
