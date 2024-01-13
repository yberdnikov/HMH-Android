package com.hmh.hamyeonham.challenge.generate

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hmh.hamyeonham.common.databinding.FragmentSelectScreentimeBinding
import com.hmh.hamyeonham.common.view.setupScreentimeGoalRange
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.challenge.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChallengeTimeSelectFragment : Fragment() {
    private val binding by viewBinding(FragmentSelectScreentimeBinding::bind)
    private val activityViewModel by activityViewModels<ChallengeGenerateViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSelectScreentimeBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.run {
            tvScreentimeGoalTitle.text = getString(R.string.screentime_goal_title)
            tvScreentimeGoalDescription.text = getString(R.string.screentime_goal_description)

            npScreentimeGoal.setupScreentimeGoalRange(1, 6)

            npScreentimeGoal.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS

            npScreentimeGoal.setOnValueChangedListener { _, _, newTime ->
                Log.d("NP", "useTotalTime: $newTime")
                activityViewModel.updateChallenge {
                    copy(goalTime = newTime)
                }
            }
        }
    }
}
