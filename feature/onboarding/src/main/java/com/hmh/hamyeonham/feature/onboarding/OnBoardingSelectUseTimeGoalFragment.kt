package com.hmh.hamyeonham.feature.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.onboarding.databinding.FragmentOnBoardingSelectUseTimeGoalBinding

class OnBoardingSelectUseTimeGoalFragment : Fragment() {
    private val binding by viewBinding(FragmentOnBoardingSelectUseTimeGoalBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return FragmentOnBoardingSelectUseTimeGoalBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            npOnboardingUseTimeGoalHour.minValue = 1
            npOnboardingUseTimeGoalHour.maxValue = 6
            npOnboardingUseTimeGoalMinute.minValue = 0
            npOnboardingUseTimeGoalMinute.maxValue = 59
        }
    }
}
