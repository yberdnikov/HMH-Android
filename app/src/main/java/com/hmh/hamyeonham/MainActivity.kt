package com.hmh.hamyeonham

import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.hmh.hamyeonham.common.context.getAppNameFromPackageName
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreen = installSplashScreen()
        initSplashAnimation(splashScreen)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            viewModel.usageStatsList.collect {
                it.forEach {
                    Log.d(
                        "MainActivity",
                        "packageName: ${getAppNameFromPackageName(it.packageName)}"
                    )
                    Log.d("MainActivity", "totalTimeInForeground: ${it.totalTimeInForeground}")
                }
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
