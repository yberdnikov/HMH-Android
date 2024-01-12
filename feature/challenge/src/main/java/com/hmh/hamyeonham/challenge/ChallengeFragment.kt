package com.hmh.hamyeonham.challenge

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hmh.hamyeonham.challenge.appadd.AppAddActivity
import com.hmh.hamyeonham.challenge.calendar.ChallengeCalendarAdapter
import com.hmh.hamyeonham.challenge.goals.ChallengeUsageGoalsAdapter
import com.hmh.hamyeonham.common.fragment.viewLifeCycle
import com.hmh.hamyeonham.common.fragment.viewLifeCycleScope
import com.hmh.hamyeonham.common.view.VerticalSpaceItemDecoration
import com.hmh.hamyeonham.common.view.dp
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.core.MainViewModel
import com.hmh.hamyeonham.feature.challenge.databinding.FragmentChallengeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ChallengeFragment : Fragment() {
    private val binding by viewBinding(FragmentChallengeBinding::bind)
    private val activityViewModel by activityViewModels<MainViewModel>()
    private lateinit var appselectionResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentChallengeBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initChallengeRecyclerView()
        initChallengeGoalsRecyclerView()
        initResultLauncher()
        collectMainState()
    }

    private fun collectMainState() {
        val challengeAdapter = binding.rvChallengeCalendar.adapter as? ChallengeCalendarAdapter
        val challengeGoalsAdapter = binding.rvAppUsageGoals.adapter as? ChallengeUsageGoalsAdapter
        activityViewModel.mainState.flowWithLifecycle(viewLifeCycle).onEach {
            challengeAdapter?.submitList(it.challengeStatus.isSuccessList)
            challengeGoalsAdapter?.submitList(it.usageGoals)
        }.launchIn(viewLifeCycleScope)
    }

    private fun initChallengeRecyclerView() {
        binding.rvChallengeCalendar.run {
            layoutManager = GridLayoutManager(requireContext(), 7)
            adapter = ChallengeCalendarAdapter()
        }
    }

    private fun initResultLauncher() {
        appselectionResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val selectedApps = result.data?.getStringArrayExtra("selectedApps")
                    if (selectedApps != null) {
                        for (i in selectedApps)
                            Log.d("selected apps", i)
                    }
                }
            }
    }

    private fun initChallengeGoalsRecyclerView() {
        binding.rvAppUsageGoals.run {
            adapter = ChallengeUsageGoalsAdapter(onAppListAddClicked = {
                val intent = Intent(requireContext(), AppAddActivity::class.java)
                appselectionResultLauncher.launch(intent)
            })
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(VerticalSpaceItemDecoration(9.dp))
        }
    }
}
