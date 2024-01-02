package com.hmh.hamyeonham

import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.hmh.hamyeonham.common.context.getAppNameFromPackageName
import com.hmh.hamyeonham.common.time.getCurrentDayStartEndEpochMillis
import com.hmh.hamyeonham.databinding.ActivitySampleBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SampleActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivitySampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySampleBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        initSplashAnimation(splashScreen)
        setContentView(R.layout.activity_sample)
        lifecycleScope.launch {
            viewModel.usageStatsList.collect {
                it.forEach {
                    Log.d(
                        "MainActivity",
                        "packageName: ${getAppNameFromPackageName(it.packageName)}",
                    )
                    Log.d("MainActivity", "totalTimeInForeground: ${it.totalTimeInForeground}")
                }
            }
        }
        btUsageClick()
    }

    private fun btUsageClick() {
        val button = findViewById<Button>(R.id.bt_usagestat)
        button.setOnClickListener {
            Log.d("bt listener", "start hrere")
            val (startTime, endTime) = getCurrentDayStartEndEpochMillis()
            viewModel.getUsageStats(startTime, endTime)
            for (i in 0..1) {
                val us = viewModel.usageStatsList.value[i]
                Log.d("usage id", us.packageName.toString())
                Log.d("usage time", us.totalTimeInForeground.toString())
            }
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
}
