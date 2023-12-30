package com.hmh.hamyeonham

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.hmh.hamyeonham.usagestats.datasource.UsageStatsDataSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var usageStatsDataSource: UsageStatsDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreen = installSplashScreen()
        initSplashAnimation(splashScreen)
        setContentView(R.layout.activity_main)
        // todo - 리사이클러뷰 구현
//        initAdapter()
        initUsageStat()
    }

//    private fun initAdapter() {
//        val usagestatAdapter = UsagestatAdapter(viewModel.mockAppUsageList)
//        binding.rvUsagestat.adapter = usagestatAdapter
//        binding.rvUsagestat.addItemDecoration(CustomItemDecoration())
//    }

    private fun initUsageStat() {
        val currentDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val startDate =
            LocalDate(currentDateTime.year, currentDateTime.monthNumber, currentDateTime.dayOfMonth)
        val startOfDay =
            LocalDateTime(startDate.year, startDate.monthNumber, startDate.dayOfMonth, 0, 0)
        val endOfDay =
            LocalDateTime(startDate.year, startDate.monthNumber, startDate.dayOfMonth, 23, 59, 59)

        val startTime = startOfDay.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
        val endTime = endOfDay.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()

        val list = usageStatsDataSource.getUsageStats(startTime, endTime)
        list.filter { it.totalTimeInForeground > 0 }.sortedByDescending { it.totalTimeInForeground }
            .forEach {
                Log.d(
                    "usageStatsDataSource",
                    "onCreate: ${getAppNameFromPackageName(it.packageName)} ${it.totalTimeInForeground}",
                )
            }
    }

    private fun initSplashAnimation(splashScreen: SplashScreen) {
        splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->
            val splashScreenView = splashScreenViewProvider.view
            val fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)

            fadeOut.setAnimationListener(
                object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}

                    override fun onAnimationEnd(animation: Animation) {
                        splashScreenViewProvider.remove()
                    }

                    override fun onAnimationRepeat(animation: Animation) {}
                },
            )
            splashScreenView.startAnimation(fadeOut)
        }
    }

    fun getAppNameFromPackageName(packageName: String): String {
        return packageManager.getApplicationLabel(
            packageManager.getApplicationInfo(
                packageName,
                PackageManager.GET_META_DATA,
            ),
        ).toString()
    }
}
