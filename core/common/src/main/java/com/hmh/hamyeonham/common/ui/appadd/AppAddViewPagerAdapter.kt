package com.hmh.hamyeonham.common.ui.appadd

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hmh.hamyeonham.common.ui.appadd.appselection.AppSelectionFragment
import com.hmh.hamyeonham.common.ui.appadd.time.SetGoalTimeFragment

class AppAddViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AppSelectionFragment()
            else -> SetGoalTimeFragment()
        }
    }
}
