package com.hmh.hamyeonham.feature.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.hmh.hamyeonham.common.time.getCurrentDayStartEndEpochMillis
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

import com.hmh.hamyeonham.usagestats.usecase.GetUsageStatsListUseCase
class MainViewModel @Inject constructor(
    private val userInfoRepository: UserInfoRepository,
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
