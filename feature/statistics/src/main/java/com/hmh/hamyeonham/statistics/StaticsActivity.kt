package com.hmh.hamyeonham.statistics

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.hmh.hamyeonham.common.time.convertTimeToString
import com.hmh.hamyeonham.common.time.getLeftTimeInString
import com.hmh.hamyeonham.common.time.getUsedPercentage
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
            staticsViewModel.usageStatList.collect {
                usageStaticsAdapter.submitList(it)
                bindTotalUsage()
            }
        }
    }

    private fun bindTotalUsage() {
        val usageStatAndGoal = staticsViewModel.usageStatList.value[0]
        binding.tvStaticsHour.text = convertTimeToString(usageStatAndGoal.goalTime)
        binding.pbStatics.setProgress(getUsedPercentage(usageStatAndGoal.totalTimeInForeground, usageStatAndGoal.goalTime))
        binding.tvStaticsLeftHour.text = getLeftTimeInString(usageStatAndGoal.totalTimeInForeground, usageStatAndGoal.goalTime)
    }
}
