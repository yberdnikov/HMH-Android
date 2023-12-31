package com.hmh.hamyeonham

import androidx.lifecycle.ViewModel
import com.hmh.hamyeonham.common.time.getCurrentDayStartEndEpochMillis
import com.hmh.hamyeonham.usagestats.model.UsageStat
import com.hmh.hamyeonham.usagestats.repository.UsageStatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val usageStatsRepository: UsageStatsRepository
) : ViewModel() {

    val usageStatsList = MutableStateFlow<List<UsageStat>>(emptyList())

    init {
        val (startTime, endTime) = getCurrentDayStartEndEpochMillis()
        getUsageStats(startTime, endTime)
    }

    fun getUsageStats(startTime: Long, endTime: Long) {
        usageStatsList.value = usageStatsRepository.getUsageStats(startTime, endTime)
    }
}
