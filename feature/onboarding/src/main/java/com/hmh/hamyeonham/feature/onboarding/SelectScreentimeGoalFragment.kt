package com.hmh.hamyeonham.feature.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.onboarding.databinding.FragmentSelectScreentimeGoalBinding

class SelectScreentimeGoalFragment : Fragment() {
    private val binding by viewBinding(FragmentSelectScreentimeGoalBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return FragmentSelectScreentimeGoalBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            npOnboardingScreentimeGoal.minValue = 1
            npOnboardingScreentimeGoal.maxValue = 6
        }
    }
}
