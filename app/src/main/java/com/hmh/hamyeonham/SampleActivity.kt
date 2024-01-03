package com.hmh.hamyeonham

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.hmh.hamyeonham.databinding.ActivitySampleBinding
import com.hmh.hamyeonham.statistics.StaticsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SampleActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySampleBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        initSplashAnimation(splashScreen)
        setContentView(binding.root)
        Intent(this, StaticsActivity::class.java).let(::startActivity)
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
