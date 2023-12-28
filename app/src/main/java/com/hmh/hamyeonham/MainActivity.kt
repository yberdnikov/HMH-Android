package com.hmh.hamyeonham

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
}

