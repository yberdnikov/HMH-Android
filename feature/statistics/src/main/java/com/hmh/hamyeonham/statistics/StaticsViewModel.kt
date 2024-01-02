package com.hmh.hamyeonham.feature.statistics

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
        val usageStatsList = MutableStateFlow<List<UsageStat>>(emptyList())
        val appNameList =
            listOf<String>(
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

        fun getUsageStats(
            startTime: Long,
            endTime: Long,
        ) {
            usageStatsList.value = usageStatsRepository.getUsageStats(startTime, endTime)
        }
    }
