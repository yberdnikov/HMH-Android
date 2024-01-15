package com.hmh.hamyeonham.feature.onboarding.fragment

import android.os.Bundle
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
    private var useTotalTime: Long = 0L

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentOnBoardingSelectUseTimeBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNumberPicker()
        handleNumberPickerValue()
    }

    private fun handleNumberPickerValue() {
        binding.npOnboardingUseTimeGoalHour.setOnValueChangedListener { _, _, newTime ->
            useTotalTime = (newTime * 60 + binding.npOnboardingUseTimeGoalMinute.value).timeToMs()
            updateViewModel()
        }
        binding.npOnboardingUseTimeGoalMinute.setOnValueChangedListener { _, _, newTime ->
            useTotalTime = (binding.npOnboardingUseTimeGoalHour.value * 60 + newTime).timeToMs()
            updateViewModel()
        }
    }

    private fun setNumberPicker() {
        binding.run {
            npOnboardingUseTimeGoalHour.setupScreentimeGoalRange(0, 1)
            npOnboardingUseTimeGoalMinute.setupScreentimeGoalRange(0, 59)
            npOnboardingUseTimeGoalHour.descendantFocusability =
                NumberPicker.FOCUS_BLOCK_DESCENDANTS
        }
    }

    private fun updateViewModel() {
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
