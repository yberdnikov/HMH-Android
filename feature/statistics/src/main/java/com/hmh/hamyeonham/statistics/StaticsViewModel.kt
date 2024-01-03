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

        init {
            val (startTime, endTime) = getCurrentDayStartEndEpochMillis()
            getStatics(startTime, endTime)
//            for (i in usageStatList.value) {
//                Log.d("app name", i.packageName)
//                Log.d("total time", i.totalTimeInForeground.toString())
//                Log.d("goal time", i.goalTime.toString())
//            }
        }

        private fun getStatics(
            startTime: Long,
            endTime: Long,
        ) {
            usageStatList.value = staticsUseCase.getStatics(startTime, endTime)
        }
    }
