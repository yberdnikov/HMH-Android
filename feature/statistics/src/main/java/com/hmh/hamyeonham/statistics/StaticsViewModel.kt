package com.hmh.hamyeonham.statistics

import android.util.Log
import androidx.lifecycle.ViewModel
import com.hmh.hamyeonham.common.time.getCurrentDayStartEndEpochMillis
import com.hmh.hamyeonham.usagestats.model.UsageStatAndGoal
import com.hmh.hamyeonham.usagestats.usecase.GetUsageStatsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class StaticsViewModel @Inject constructor(
    private val getUsageStatsListUseCase: GetUsageStatsListUseCase
) : ViewModel() {
    val usageStatAndGoalList = MutableStateFlow<List<UsageStatAndGoal>>(emptyList())

    init {
        val (startTime, endTime) = getCurrentDayStartEndEpochMillis()
        getUsageStatsAndGoals(startTime, endTime)
    }

    private fun getUsageStatsAndGoals(startTime: Long, endTime: Long) {
        usageStatAndGoalList.value =
            getUsageStatsListUseCase.getUsageStatsAndGoals(startTime, endTime)
        Log.d("usageStatAndGoalList.value", usageStatAndGoalList.value.toString())
        usageStatAndGoalList.value.forEach {
            Log.d("usage.packageName", it.packageName)
            Log.d("usage.totalTimeInForeground", it.totalTimeInForeground.toString())
            Log.d("usage.goalTime", it.goalTime.toString())
            Log.d("usage.timeLeft", it.timeLeft.toString())
            Log.d("usage.usedPercentage", it.usedPercentage.toString())
        }
    }
}
