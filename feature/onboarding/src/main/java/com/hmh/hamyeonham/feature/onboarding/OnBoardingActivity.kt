package com.hmh.hamyeonham.feature.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.onboarding.databinding.ActivityOnBoardingBinding
import com.hmh.hamyeonham.feature.onboarding.viewModel.OnBoardingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class OnBoardingActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityOnBoardingBinding::inflate)
    private val viewModel by viewModels<OnBoardingViewModel>()
    private lateinit var onBackPressedCallback: OnBackPressedCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViewPager()
        checkNextButtonEnable()
        setBackPressedCallback()
    }

    private fun initViewPager() {
        val pagerAdapter = setOnboardingPageAdapter()
        binding.btnOnboardingNext.setOnClickListener {
            viewModel.initializeButtonStates()
            navigateToNextOnboardingStep(pagerAdapter)
        }
        binding.ivOnboardingBack.setOnClickListener {
            viewModel.initializeButtonStates()
            navigateToPreviousOnboardingStep()
        }
    }

    private fun navigateToPreviousOnboardingStep() {
        binding.vpOnboardingContainer.let { viewPager ->
            val currentItem = viewPager.currentItem
            if (currentItem > 0) {
                viewPager.currentItem = currentItem - 1
            } else {
                onBackPressedCallback.isEnabled = false
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    private fun navigateToNextOnboardingStep(pagerAdapter: OnBoardingFragmentStateAdapter) {
        binding.vpOnboardingContainer.let { viewPager ->
            val currentItem = viewPager.currentItem
            val lastItem = pagerAdapter.itemCount - 1
            when {
                currentItem < lastItem -> viewPager.currentItem = currentItem + 1
                currentItem == lastItem -> startOnBoardingDoneSignUpActivity()
            }
        }
    }

    private fun checkNextButtonEnable() {
        viewModel.clickNextButtonEnable.flowWithLifecycle(lifecycle).onEach { clickNextButtonEnable ->
            binding.btnOnboardingNext.isEnabled = clickNextButtonEnable
            binding.btnOnboardingNext.isSelected = clickNextButtonEnable
        }.launchIn(lifecycleScope)
    }

    private fun setBackPressedCallback() {
        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navigateToPreviousOnboardingStep()
            }
        }
    }

    private fun startOnBoardingDoneSignUpActivity() {
        val intent = Intent(this, OnBoardingDoneSingUpActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
        finish()
    }

    private fun setOnboardingPageAdapter(): OnBoardingFragmentStateAdapter {
        val pagerAdapter = OnBoardingFragmentStateAdapter(this)
        binding.vpOnboardingContainer.run {
            adapter = pagerAdapter
            isUserInputEnabled = false
        }
        return pagerAdapter
    }

    override fun onDestroy() {
        onBackPressedCallback.remove()
        super.onDestroy()
    }
}
