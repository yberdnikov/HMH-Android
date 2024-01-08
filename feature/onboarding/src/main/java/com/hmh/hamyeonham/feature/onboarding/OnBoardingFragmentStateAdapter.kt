package com.hmh.hamyeonham.feature.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hmh.hamyeonham.feature.onboarding.fragment.OnBoardingRequestPermissionFragment
import com.hmh.hamyeonham.feature.onboarding.fragment.OnBoardingSelectAppFragment
import com.hmh.hamyeonham.feature.onboarding.fragment.OnBoardingSelectDataFragment
import com.hmh.hamyeonham.feature.onboarding.fragment.OnBoardingSelectScreenTimeFragment

enum class OnBoardingFragmentType {
    SELECT_DATA_TIME,
    SELECT_DATA_PROBLEM,
    SELECT_DATA_PERIOD,
    SELECT_SCREEN_TIME_GOAL,
    REQUEST_PERMISSION,
    SELECT_APP,
    SELECT_USE_TIME_GOAL,
}

class OnBoardingFragmentStateAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return OnBoardingFragmentType.entries.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (val fragmentType = position.toOnBoardingFragmentType()) {
            OnBoardingFragmentType.SELECT_DATA_TIME -> OnBoardingSelectDataFragment.newInstance(
                fragmentType,
            )

            OnBoardingFragmentType.SELECT_DATA_PROBLEM -> OnBoardingSelectDataFragment.newInstance(
                fragmentType,
            )

            OnBoardingFragmentType.SELECT_DATA_PERIOD -> OnBoardingSelectDataFragment.newInstance(
                fragmentType,
            )

            OnBoardingFragmentType.SELECT_SCREEN_TIME_GOAL -> OnBoardingSelectScreenTimeFragment()
            OnBoardingFragmentType.REQUEST_PERMISSION -> OnBoardingRequestPermissionFragment()
            OnBoardingFragmentType.SELECT_APP -> OnBoardingSelectAppFragment()
            OnBoardingFragmentType.SELECT_USE_TIME_GOAL -> OnBoardingSelectScreenTimeFragment()
        }
    }

    private fun Int.toOnBoardingFragmentType(): OnBoardingFragmentType {
        return OnBoardingFragmentType.entries.getOrNull(this)
            ?: OnBoardingFragmentType.SELECT_DATA_TIME
    }
}
