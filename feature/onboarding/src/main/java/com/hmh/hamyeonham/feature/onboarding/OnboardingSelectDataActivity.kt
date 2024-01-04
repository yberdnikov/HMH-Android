package com.hmh.hamyeonham.feature.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hmh.hamyeonham.feature.onboarding.databinding.ActivityOnboardingSelectDataBinding

class OnboardingSelectDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingSelectDataBinding
    private lateinit var fragmentHandler: OnboardingFragmentHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingSelectDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initOnboardingDataFragment()
    }

    private fun initOnboardingDataFragment() {
        fragmentHandler = OnboardingFragmentHandler(
            supportFragmentManager,
            binding.flOnboardingContainer.id,
        )

        val (fragment1, fragment2) =
            fragmentHandler.setOnboardingFragments()

        fragmentHandler.addFragment(fragment1, "fragment1_tag")
        binding.btnOnboardingNext.setOnClickListener {
            fragmentHandler.replaceFragment(fragment2, "fragment2_tag")
        }
    }
}
