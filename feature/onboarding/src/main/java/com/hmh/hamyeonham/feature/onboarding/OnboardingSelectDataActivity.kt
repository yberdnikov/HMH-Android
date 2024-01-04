package com.hmh.hamyeonham.feature.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.hmh.hamyeonham.feature.onboarding.OnboardingQuestionList.OnboardingQuestionProblem
import com.hmh.hamyeonham.feature.onboarding.OnboardingQuestionList.OnboardingQuestionTime
import com.hmh.hamyeonham.feature.onboarding.databinding.ActivityOnboardingSelectDataBinding

class OnboardingSelectDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingSelectDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingSelectDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val (fragment1, fragment2) = setOnboardingFragment()

        addFragment(fragment1, "fragment1_tag")
        binding.btnOnboardingNext.setOnClickListener {
            replaceFragment(fragment2, "fragment2_tag")
        }
    }

    private fun setOnboardingFragment(): Pair<OnboardingSelectDataFragment, OnboardingSelectDataFragment> {
        val fragment1 = OnboardingSelectDataFragment.newInstance(
            OnboardingQuestionTime[0],
            OnboardingQuestionTime[1],
            OnboardingQuestionTime[2],
            OnboardingQuestionTime[3],
            OnboardingQuestionTime[4],
        )

        val fragment2 = OnboardingSelectDataFragment.newInstance(
            OnboardingQuestionProblem[0],
            OnboardingQuestionProblem[1],
            OnboardingQuestionProblem[2],
            OnboardingQuestionProblem[3],
            OnboardingQuestionProblem[4],
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
