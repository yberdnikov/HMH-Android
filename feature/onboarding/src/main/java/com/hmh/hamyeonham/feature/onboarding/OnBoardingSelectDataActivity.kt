package com.hmh.hamyeonham.feature.onboarding

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.onboarding.databinding.ActivityOnBoardingSelectDataBinding

class OnBoardingSelectDataActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityOnBoardingSelectDataBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val pagerAdapter = setOnboardingPageAdapter()

        binding.btnOnboardingNext.setOnClickListener {
            setOnboardingViewPager(pagerAdapter)
        }
    }

    private fun setOnboardingViewPager(pagerAdapter: OnBoardingFragmentStateAdapter) {
        val currentItem = binding.vpOnboardingContainer.currentItem
        if (currentItem < pagerAdapter.itemCount - 1) {
            binding.vpOnboardingContainer.currentItem = currentItem + 1
        }
        if (currentItem == pagerAdapter.itemCount - 1) {
            pagerAdapter.removeOnboardingFragments()
            startActivity(
                Intent(this, OnBoardingDoneSingUpActivity::class.java).addFlags(
                    FLAG_ACTIVITY_NO_ANIMATION,
                ),
            )
        }
    }

    private fun setOnboardingPageAdapter(): OnBoardingFragmentStateAdapter {
        val pagerAdapter = OnBoardingFragmentStateAdapter(this).apply {
            addOnboardingFragment(OnBoardingFragmentType.SELECT_DATA_TIME)
            addOnboardingFragment(OnBoardingFragmentType.SELECT_DATA_PROBLEM)
            addOnboardingFragment(OnBoardingFragmentType.SELECT_DATA_PERIOD)
            addOnboardingFragment(OnBoardingFragmentType.SELECT_SCREENTIME_GOAL)
            addOnboardingFragment(OnBoardingFragmentType.REQUEST_PERMISSION)
            addOnboardingFragment(OnBoardingFragmentType.SELECT_APP)
            addOnboardingFragment(OnBoardingFragmentType.SELECT_USE_TIME_GOAL)
        }

        binding.vpOnboardingContainer.adapter = pagerAdapter
        binding.vpOnboardingContainer.isUserInputEnabled = false
        return pagerAdapter
    }
}
