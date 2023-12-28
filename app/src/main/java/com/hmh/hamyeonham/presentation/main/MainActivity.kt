package com.hmh.hamyeonham.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hmh.hamyeonham.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nhf_main) as NavHostFragment
//        val navController = navHostFragment.navController
//        val bottomNav = findViewById<BottomNavigationView>(R.id.btn_main)
//        bottomNav.setupWithNavController(navController)
    }
}
