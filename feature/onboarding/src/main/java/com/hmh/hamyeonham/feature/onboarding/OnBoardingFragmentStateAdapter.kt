package com.hmh.hamyeonham.feature.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnBoardingFragmentStateAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    var onBoardingFragments: ArrayList<Fragment> = ArrayList()
    override fun getItemCount(): Int {
        return onBoardingFragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return onBoardingFragments[position]
    }

    fun removeOnboardingFragments() {
        onBoardingFragments.removeLast()
    }

    fun addOnboardingFragment(fragmentType: OnBoardingFragmentType) {
        val fragment = when (fragmentType) {
            OnBoardingFragmentType.REQUEST_PERMISSION -> OnBoardingRequestPermissionFragment()
            OnBoardingFragmentType.SELECT_APP -> OnBoardingSelectAppFragment()
            OnBoardingFragmentType.SELECT_SCREENTIME_GOAL -> OnBoardingSelectScreentimeGoalFragment()
            OnBoardingFragmentType.SELECT_DATA_TIME -> OnBoardingSelectDataFragment.newInstance(
                OnBoardingQuestionList.OnBoardingQuestionTime[0],
                OnBoardingQuestionList.OnBoardingQuestionTime[1],
                OnBoardingQuestionList.OnBoardingQuestionTime[2],
                OnBoardingQuestionList.OnBoardingQuestionTime[3],
                OnBoardingQuestionList.OnBoardingQuestionTime[4],
                OnBoardingQuestionList.OnBoardingQuestionTime[5],
            )

            OnBoardingFragmentType.SELECT_DATA_PROBLEM -> OnBoardingSelectDataFragment.newInstance(
                OnBoardingQuestionList.OnBoardingQuestionProblem[0],
                OnBoardingQuestionList.OnBoardingQuestionProblem[1],
                OnBoardingQuestionList.OnBoardingQuestionProblem[2],
                OnBoardingQuestionList.OnBoardingQuestionProblem[3],
                OnBoardingQuestionList.OnBoardingQuestionProblem[4],
                OnBoardingQuestionList.OnBoardingQuestionProblem[5],
            )

            OnBoardingFragmentType.SELECT_DATA_PERIOD -> OnBoardingSelectDataFragment.newInstance(
                OnBoardingQuestionList.OnBoardingChallengePeriod[0],
                OnBoardingQuestionList.OnBoardingChallengePeriod[1],
                OnBoardingQuestionList.OnBoardingChallengePeriod[2],
                OnBoardingQuestionList.OnBoardingChallengePeriod[3],
                OnBoardingQuestionList.OnBoardingChallengePeriod[4],
                OnBoardingQuestionList.OnBoardingChallengePeriod[5],
            )

            OnBoardingFragmentType.SELECT_USE_TIME_GOAL -> OnBoardingSelectUseTimeGoalFragment()
            else -> {
                throw IllegalArgumentException("Unknown fragment type")
            }
        }
        onBoardingFragments.add(fragment)
    }
}
