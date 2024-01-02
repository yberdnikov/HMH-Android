package com.hmh.hamyeonham

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.hmh.hamyeonham.databinding.ActivitySampleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SampleActivity : AppCompatActivity() {
    private val viewModel by viewModels<StaticsViewModel>()
    private lateinit var binding: ActivitySampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySampleBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        initSplashAnimation(splashScreen)
//        lifecycleScope.launch {
//            viewModel.totalUsageStatsList.collect {
//                it.forEach {
//                    if (it.packageName in viewModel.mockAppNameList) {
//                        Log.d(
//                            "MainActivity",
//                            "packageName: ${it.packageName}",
//                        )
//                        Log.d(
//                            "MainActivity",
//                            "appName: ${getAppNameFromPackageName(it.packageName)}",
//                        )
//                        Log.d("MainActivity", "totalTimeInForeground: ${it.totalTimeInForeground}")
//                    }
//                }
//            }
//        }
        val usageStaticsAdapter = UsageStaticsAdapter(viewModel.getSelectedUsageStatList(), this)
        binding.rvUsagestat.adapter = usageStaticsAdapter
//        binding.rvUsagestat.addItemDecoration(CustomItemDecoration())
        setContentView(R.layout.activity_sample)
//        Intent(this, StaticsActivity::class.java).let(::startActivity)
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
