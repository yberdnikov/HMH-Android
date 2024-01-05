package com.hmh.hamyeonham.feature.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hmh.hamyeonham.common.activity.addFragment
import com.hmh.hamyeonham.common.activity.replaceFragment
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.onboarding.databinding.ActivityOnboardingSelectDataBinding

class OnboardingSelectDataActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityOnboardingSelectDataBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // initOnboardingDataFragment()
    }

    private fun initOnboardingDataFragment() {

        val (fragment1, fragment2) =
            setOnboardingFragments()

        addFragment(binding.vpOnboardingContainer.id, fragment1)
        binding.btnOnboardingNext.setOnClickListener {
            replaceFragment(binding.vpOnboardingContainer.id, fragment2)
        }
    }

    private fun setOnboardingFragments(): Pair<OnboardingSelectDataFragment, OnboardingSelectDataFragment> {
        val fragment1 = OnboardingSelectDataFragment.newInstance(
            OnboardingQuestionList.OnboardingQuestionTime[0],
            OnboardingQuestionList.OnboardingQuestionTime[1],
            OnboardingQuestionList.OnboardingQuestionTime[2],
            OnboardingQuestionList.OnboardingQuestionTime[3],
            OnboardingQuestionList.OnboardingQuestionTime[4],
        )
        val fragment2 = OnboardingSelectDataFragment.newInstance(
            OnboardingQuestionList.OnboardingQuestionProblem[0],
            OnboardingQuestionList.OnboardingQuestionProblem[1],
            OnboardingQuestionList.OnboardingQuestionProblem[2],
            OnboardingQuestionList.OnboardingQuestionProblem[3],
            OnboardingQuestionList.OnboardingQuestionProblem[4],
        )
        return Pair(fragment1, fragment2)
    }
}
