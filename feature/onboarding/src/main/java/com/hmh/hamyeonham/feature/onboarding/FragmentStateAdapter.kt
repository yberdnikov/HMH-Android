package com.hmh.hamyeonham.feature.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentStateAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    var onboardingFragments: ArrayList<Fragment> = ArrayList()
    override fun getItemCount(): Int {
        return onboardingFragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return onboardingFragments[position]
    }

    fun removeOnboardingFragments() {
        onboardingFragments.removeLast()
    }

    fun addOnboardingFragment(fragmentType: OnboardingFragmentType) {
        val fragment = when (fragmentType) {
            OnboardingFragmentType.REQUEST_PERMISSION -> RequestPermissionFragment()
            OnboardingFragmentType.SELECT_APP -> SelectAppFragment()
            OnboardingFragmentType.SELECT_SCREENTIME_GOAL -> SelectScreentimeGoalFragment()
            OnboardingFragmentType.SELECT_DATA_TIME -> OnboardingSelectDataFragment.newInstance(
                OnboardingQuestionList.OnboardingQuestionTime[0],
                OnboardingQuestionList.OnboardingQuestionTime[1],
                OnboardingQuestionList.OnboardingQuestionTime[2],
                OnboardingQuestionList.OnboardingQuestionTime[3],
                OnboardingQuestionList.OnboardingQuestionTime[4],
                OnboardingQuestionList.OnboardingQuestionTime[5],
            )

            OnboardingFragmentType.SELECT_DATA_PROBLEM -> OnboardingSelectDataFragment.newInstance(
                OnboardingQuestionList.OnboardingQuestionProblem[0],
                OnboardingQuestionList.OnboardingQuestionProblem[1],
                OnboardingQuestionList.OnboardingQuestionProblem[2],
                OnboardingQuestionList.OnboardingQuestionProblem[3],
                OnboardingQuestionList.OnboardingQuestionProblem[4],
                OnboardingQuestionList.OnboardingQuestionProblem[5],
            )

            OnboardingFragmentType.SELECT_DATA_PERIOD -> OnboardingSelectDataFragment.newInstance(
                OnboardingQuestionList.OnboardingChallengePeriod[0],
                OnboardingQuestionList.OnboardingChallengePeriod[1],
                OnboardingQuestionList.OnboardingChallengePeriod[2],
                OnboardingQuestionList.OnboardingChallengePeriod[3],
                OnboardingQuestionList.OnboardingChallengePeriod[4],
                OnboardingQuestionList.OnboardingChallengePeriod[5],
            )

            OnboardingFragmentType.SELECT_USE_TIME_GOAL -> SelectUseTimeGoalFragment()
            else -> {
                throw IllegalArgumentException("Unknown fragment type")
            }
        }
        onboardingFragments.add(fragment)
    }
}
