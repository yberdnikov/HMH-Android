package com.hmh.hamyeonham.feature.onboarding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hmh.hamyeonham.common.view.setupScreentimeGoalRange
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.onboarding.databinding.FragmentOnBoardingSelectScreentimeBinding
import com.hmh.hamyeonham.feature.onboarding.viewmodel.OnBoardingViewModel

class OnBoardingSelectScreenTimeFragment : Fragment() {
    private val binding by viewBinding(FragmentOnBoardingSelectScreentimeBinding::bind)
    private val activityViewModel by activityViewModels<OnBoardingViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentOnBoardingSelectScreentimeBinding.inflate(
            inflater,
            container,
            false,
        ).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.npOnboardingScreentimeGoal.setupScreentimeGoalRange(1, 6)

        binding.npOnboardingScreentimeGoal.descendantFocusability =
            NumberPicker.FOCUS_BLOCK_DESCENDANTS


        binding.npOnboardingScreentimeGoal.setOnValueChangedListener { _, _, newTime ->
            activityViewModel.run {
                updateUserResponses {
                    copy(goalTime = newTime)
                }
                updateState {
                    copy(isNextButtonActive = true)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        activityViewModel.updateState {
            copy(isNextButtonActive = true)
        }
    }
}
