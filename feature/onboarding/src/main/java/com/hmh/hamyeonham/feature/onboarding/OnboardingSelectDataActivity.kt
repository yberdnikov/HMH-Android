package com.hmh.hamyeonham.feature.onboarding

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.onboarding.databinding.ActivityOnboardingSelectDataBinding

class OnboardingSelectDataActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityOnboardingSelectDataBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val pagerAdapter = setOnboardingPageAdapter()

        binding.btnOnboardingNext.setOnClickListener {
            setOnboardingViewPager(pagerAdapter)
        }
    }

    private fun setOnboardingViewPager(pagerAdapter: FragmentStateAdapter) {
        val currentItem = binding.vpOnboardingContainer.currentItem
        if (currentItem < pagerAdapter.itemCount - 1) {
            binding.vpOnboardingContainer.currentItem = currentItem + 1
        }
        if (currentItem == pagerAdapter.itemCount - 1) {
            pagerAdapter.removeOnboardingFragments()
            startActivity(
                Intent(this, DoneSingUpActivity::class.java).addFlags(
                    FLAG_ACTIVITY_NO_ANIMATION,
                ),
            )
        }
    }

    private fun setOnboardingPageAdapter(): FragmentStateAdapter {
        val pagerAdapter = FragmentStateAdapter(this).apply {
            addOnboardingFragment(OnboardingFragmentType.SELECT_DATA_TIME)
            addOnboardingFragment(OnboardingFragmentType.SELECT_DATA_PROBLEM)
            addOnboardingFragment(OnboardingFragmentType.SELECT_DATA_PERIOD)
            addOnboardingFragment(OnboardingFragmentType.SELECT_SCREENTIME_GOAL)
            addOnboardingFragment(OnboardingFragmentType.REQUEST_PERMISSION)
            addOnboardingFragment(OnboardingFragmentType.SELECT_APP)
            addOnboardingFragment(OnboardingFragmentType.SELECT_USE_TIME_GOAL)
        }

        binding.vpOnboardingContainer.adapter = pagerAdapter
        binding.vpOnboardingContainer.isUserInputEnabled = false
        return pagerAdapter
    }
}
