package com.hmh.hamyeonham.statistics

import androidx.lifecycle.ViewModel
import com.hmh.hamyeonham.common.time.getCurrentDayStartEndEpochMillis
import com.hmh.hamyeonham.usagestats.model.UsageStatAndGoal
import com.hmh.hamyeonham.usagestats.usecase.StaticsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class StaticsViewModel
    @Inject
    constructor(
        private val staticsUseCase: StaticsUseCase,
    ) : ViewModel() {
        val usageStatAndGoalList = MutableStateFlow<List<UsageStatAndGoal>>(emptyList())
        val totalUsageStatAndGoal = MutableStateFlow<UsageStatAndGoal>(UsageStatAndGoal("", 0, 0))

        init {
            val (startTime, endTime) = getCurrentDayStartEndEpochMillis()
            getUsageStatsAndGoals(startTime, endTime)
            getTotalUsageStatsAndGoals(startTime, endTime)
        }

        private fun getUsageStatsAndGoals(
            startTime: Long,
            endTime: Long,
        ) {
            usageStatAndGoalList.value = staticsUseCase.getUsageStatsAndGoals(startTime, endTime)
        }

        private fun getTotalUsageStatsAndGoals(
            startTime: Long,
            endTime: Long,
        ) {
            totalUsageStatAndGoal.value = staticsUseCase.getTotalUsageStatsAndGoals(startTime, endTime)
        }
    }
