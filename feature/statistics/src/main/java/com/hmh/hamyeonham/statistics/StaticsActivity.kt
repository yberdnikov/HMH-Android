package com.hmh.hamyeonham.statistics

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.hmh.hamyeonham.common.time.convertTimeToString
import com.hmh.hamyeonham.common.view.viewBinding
import com.hmh.hamyeonham.feature.statistics.databinding.ActivityStaticsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StaticsActivity : AppCompatActivity() {
    private val staticsViewModel by viewModels<StaticsViewModel>()
    private val binding by viewBinding(ActivityStaticsBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        collectUsageStatsList()
    }

    private fun collectUsageStatsList() {
        val usageStaticsAdapter = UsageStaticsAdapter()
        binding.rvStatics.run {
            adapter = usageStaticsAdapter
            layoutManager = LinearLayoutManager(this@StaticsActivity)
        }
        lifecycleScope.launch {
            staticsViewModel.usageStatAndGoalList.collect {
                bindUsageTotalView()
                usageStaticsAdapter.submitList(it)
            }
        }
    }

    private fun bindUsageTotalView() {
        binding.tvStaticsTotalTimeLeft.text =
            convertTimeToString(
                staticsViewModel.totalUsageStatAndGoal.value.timeLeft,
            )
        binding.tvStaticsTotalGoalTime.text =
            convertTimeToString(
                staticsViewModel.totalUsageStatAndGoal.value.goalTime,
            )
    }
}
