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

    fun addOnboardingFragments(fragment: Fragment) {
        onboardingFragments.add(fragment)
    }

    fun removeOnboardingFragments() {
        onboardingFragments.removeLast()
    }
}
