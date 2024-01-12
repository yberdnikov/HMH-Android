package com.hmh.hamyeonham.feature.onboarding.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hmh.hamyeonham.common.time.timeToMs
import com.hmh.hamyeonham.common.view.setupScreentimeGoalRange
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.onboarding.databinding.FragmentOnBoardingSelectUseTimeBinding
import com.hmh.hamyeonham.feature.onboarding.model.OnboardingAnswer
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

        binding.npOnboardingUseTimeGoalHour.descendantFocusability =
            NumberPicker.FOCUS_BLOCK_DESCENDANTS

        val useGoalHour = binding.npOnboardingUseTimeGoalHour.value
        val useGoalMinute = binding.npOnboardingUseTimeGoalMinute.value
        val useTotalTime = (useGoalHour * 60 + useGoalMinute).timeToMs()

        Log.d("OnBoardingSelectUseTimeFragment", "useTotalTime: $useTotalTime.toString()")

        activityViewModel.changeStateNextButton(true)

        activityViewModel.updateUserResponses {
            copy(
                apps = listOf(
                    OnboardingAnswer.App(
                        appCode = "",
                        goalTime = useTotalTime,
                    ),
                ),
            )
        }
    }
}
