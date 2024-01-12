package com.hmh.hamyeonham.challenge.appadd.time

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hmh.hamyeonham.challenge.appadd.AppAddViewModel
import com.hmh.hamyeonham.common.view.setupScreentimeGoalRange
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.challenge.databinding.FragmentSetGoalTimeBinding

class SetGoalTimeFragment : Fragment() {
    private val binding by viewBinding(FragmentSetGoalTimeBinding::bind)
    private val activityViewModel by activityViewModels<AppAddViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSetGoalTimeBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.npOnboardingScreentimeGoal.setupScreentimeGoalRange(1, 6)
        binding.npOnboardingScreentimeGoal.descendantFocusability =
            NumberPicker.FOCUS_BLOCK_DESCENDANTS
        binding.npOnboardingScreentimeGoal.setOnValueChangedListener { _, _, newTime ->
            Log.d("NP", "useTotalTime: $newTime")
        }
    }
}
