package com.hmh.hamyeonham

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreen = installSplashScreen()
        initSplashAnimation(splashScreen)

        setContentView(R.layout.activity_main)
        setBottomNavigationListener()
    }

    private fun setBottomNavigationListener() {
        binding.btn
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item1 -> {
                    // Respond to navigation item 1 click
                    true
                }

                R.id.item2 -> {
                    // Respond to navigation item 2 click
                    true
                }
                else -> false
            }
        }
    }

    private fun initSplashAnimation(splashScreen: SplashScreen) {
        splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->
            val splashScreenView = splashScreenViewProvider.view
            val fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)

            fadeOut.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}
                override fun onAnimationEnd(animation: Animation) {
                    splashScreenViewProvider.remove()
                }

                override fun onAnimationRepeat(animation: Animation) {}
            })
            splashScreenView.startAnimation(fadeOut)
        }
    }
}
