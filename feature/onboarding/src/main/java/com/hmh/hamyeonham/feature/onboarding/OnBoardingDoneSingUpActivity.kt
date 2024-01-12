package com.hmh.hamyeonham.feature.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.main.MainActivity
import com.hmh.hamyeonham.feature.onboarding.databinding.ActivityOnBoardingDoneSingUpBinding

class OnBoardingDoneSingUpActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityOnBoardingDoneSingUpBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnOnboardingDoneNext.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
