package com.hmh.hamyeonham.common.ui.appadd.time

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hmh.hamyeonham.common.ui.appadd.AppAddViewModel
import com.hmh.hamyeonham.common.databinding.FragmentSetGoalTimeBinding
import com.hmh.hamyeonham.common.view.setupScreentimeGoalRange
import com.hmh.hamyeonham.common.view.viewBinding

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
        setNumberPicker()
        setNumberPickerListener()
    }

    private fun setNumberPickerListener() {
        binding.npUseTimeGoalHour.setOnValueChangedListener { _, _, newTime ->
            val currentGoalTime = activityViewModel.state.value.goalTime
            activityViewModel.updateState {
                copy(goalTime = currentGoalTime + (newTime * 60 * 60 * 1000).toLong())
            }
        }
        binding.npUseTimeGoalMinute.setOnValueChangedListener { _, _, newTime ->
            val currentGoalTime = activityViewModel.state.value.goalTime
            activityViewModel.updateState {
                copy(goalTime = currentGoalTime + (newTime * 60 * 1000).toLong())
            }
        }
    }

    private fun setNumberPicker() {
        binding.run {
            npUseTimeGoalHour.setupScreentimeGoalRange(0, 1)
            npUseTimeGoalMinute.setupScreentimeGoalRange(0, 59)
            npUseTimeGoalHour.descendantFocusability =
                NumberPicker.FOCUS_BLOCK_DESCENDANTS
        }
    }
}
