package com.hmh.hamyeonham.feature.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hmh.hamyeonham.feature.onboarding.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.run {
            numberPicker.npCustomHours.minValue = 1
            numberPicker.npCustomHours.maxValue = 6
            numberPicker.npCustomMinutes.minValue = 1
            numberPicker.npCustomMinutes.maxValue = 59
        }
    }
}
