package com.hmh.hamyeonham.feature.onboarding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hmh.hamyeonham.common.view.setupScreentimeGoalRange
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.onboarding.databinding.FragmentOnBoardingSelectUseTimeBinding
import com.hmh.hamyeonham.feature.onboarding.viewmodel.OnBoardingViewModel

class OnBoardingSelectUseTimeFragment : Fragment() {
    private val binding by viewBinding(FragmentOnBoardingSelectUseTimeBinding::bind)
    private val activityViewModel by activityViewModels<OnBoardingViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentOnBoardingSelectUseTimeBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.npOnboardingUseTimeGoalHour.setupScreentimeGoalRange(1, 6)
        binding.npOnboardingUseTimeGoalMinute.setupScreentimeGoalRange(0, 59)
        activityViewModel.changeStateNextButton(true)
    }
}
