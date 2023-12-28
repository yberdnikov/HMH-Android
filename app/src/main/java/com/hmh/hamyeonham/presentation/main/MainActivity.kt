package com.hmh.hamyeonham.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hmh.hamyeonham.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNavigation()
    }

    private fun initNavigation() {
        val navController =
            supportFragmentManager.findFragmentById(
                androidx.navigation.fragment.R.id.nav_host_fragment_container,
            )?.findNavController()
        navController?.let {
            val bottomNav = findViewById<BottomNavigationView>(R.id.btn_main)
            bottomNav.setupWithNavController(navController)
        }
    }
}
