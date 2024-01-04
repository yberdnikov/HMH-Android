package com.hmh.hamyeonham.feature.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class OnboardingFragmentHandler @Inject constructor(
    private val fragmentManager: FragmentManager,
    private val containerId: Int,
) {

    fun setOnboardingFragments(): Pair<OnboardingSelectDataFragment, OnboardingSelectDataFragment> {
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

    fun addFragment(fragment: Fragment, tag: String) {
        fragmentManager.beginTransaction()
            .replace(containerId, fragment, tag)
            .commit()
    }

    fun replaceFragment(fragment: Fragment, tag: String) {
        fragmentManager.beginTransaction()
            .replace(containerId, fragment, tag)
            .addToBackStack(null)
            .commit()
    }
}
