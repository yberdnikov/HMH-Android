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
        val usageStatList = MutableStateFlow<List<UsageStatAndGoal>>(emptyList())
        val totalUsage = MutableStateFlow<Long>(0)

        init {
            val (startTime, endTime) = getCurrentDayStartEndEpochMillis()
            getStatics(startTime, endTime)
        }

        private fun getStatics(
            startTime: Long,
            endTime: Long,
        ) {
            val result = staticsUseCase.getStatics(startTime, endTime)
            usageStatList.value = result.first
            totalUsage.value = result.second
        }
    }
