package com.hmh.hamyeonham.feature.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hmh.hamyeonham.common.activity.addFragment
import com.hmh.hamyeonham.common.activity.replaceFragment
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.onboarding.databinding.ActivityOnboardingSelectDataBinding

class OnboardingSelectDataActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityOnboardingSelectDataBinding::inflate)
    private lateinit var fragmentHandler: OnboardingFragmentHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        addFragment(binding.flOnboardingContainer.id, fragment1)
        binding.btnOnboardingNext.setOnClickListener {
            replaceFragment(binding.flOnboardingContainer.id, fragment2)
        }
    }
}
