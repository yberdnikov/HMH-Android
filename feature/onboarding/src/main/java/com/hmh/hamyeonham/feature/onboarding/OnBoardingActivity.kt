package com.hmh.hamyeonham.feature.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.hmh.hamyeonham.feature.onboarding.OnBoardingQuestionList.OnBoardingQuestionProblem
import com.hmh.hamyeonham.feature.onboarding.OnBoardingQuestionList.OnBoardingQuestionTime
import com.hmh.hamyeonham.feature.onboarding.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val (fragment1, fragment2) = setOnboardingFragment()

        addFragment(fragment1, "fragment1_tag")
        binding.btnOnboardingNext.setOnClickListener {
            replaceFragment(fragment2, "fragment2_tag")
        }
    }

    private fun setOnboardingFragment(): Pair<OnboardingSelectDataFragment, OnboardingSelectDataFragment> {
        val fragment1 = OnboardingSelectDataFragment.newInstance(
            OnBoardingQuestionTime[0],
            OnBoardingQuestionTime[1],
            OnBoardingQuestionTime[2],
            OnBoardingQuestionTime[3],
            OnBoardingQuestionTime[4],
        )

        val fragment2 = OnboardingSelectDataFragment.newInstance(
            OnBoardingQuestionProblem[0],
            OnBoardingQuestionProblem[1],
            OnBoardingQuestionProblem[2],
            OnBoardingQuestionProblem[3],
            OnBoardingQuestionProblem[4],
        )
        return Pair(fragment1, fragment2)
    }

    private fun addFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(binding.flOnboardingContainer.id, fragment, tag)
            .commit()
    }

    private fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(binding.flOnboardingContainer.id, fragment, tag)
            .addToBackStack(null)
            .commit()
    }
}
