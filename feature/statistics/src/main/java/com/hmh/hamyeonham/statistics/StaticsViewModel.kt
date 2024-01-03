package com.hmh.hamyeonham.statistics

import androidx.lifecycle.ViewModel
import com.hmh.hamyeonham.common.time.getCurrentDayStartEndEpochMillis
import com.hmh.hamyeonham.usagestats.model.UsageStat
import com.hmh.hamyeonham.usagestats.repository.UsageStatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class StaticsViewModel
    @Inject
    constructor(
        private val usageStatsRepository: UsageStatsRepository,
    ) : ViewModel() {
        val totalUsageStatsList = MutableStateFlow<List<UsageStat>>(emptyList())
        private val mockAppNameList =
            listOf(
                // hmh
                "HMH-Android",
                // kakaotalk
                "com.kakao.talk",
                // Google Play 서비스
                "com.google.android.gms",
                // 유튜브
                "com.google.android.youtube",
                // 크롬
                "com.android.chrome",
            )

        init {
            val (startTime, endTime) = getCurrentDayStartEndEpochMillis()
            getUsageStats(startTime, endTime)
        }

        private fun getUsageStats(
            startTime: Long,
            endTime: Long,
        ) {
            totalUsageStatsList.value =
                usageStatsRepository.getUsageTimeForPackages(startTime, endTime, mockAppNameList)
        }
    }
