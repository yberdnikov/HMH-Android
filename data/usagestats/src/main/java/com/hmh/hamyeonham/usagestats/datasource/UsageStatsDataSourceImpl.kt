package com.hmh.hamyeonham.usagestats.datasource

import android.app.usage.UsageStatsManager
import com.hmh.hamyeonham.usagestats.model.UsageStatModel
import javax.inject.Inject

class UsageStatsDataSourceImpl @Inject constructor(
    private val usageStatsManager: UsageStatsManager?,
) : UsageStatsDataSource {

    override fun getUsageStats(startTime: Long, endTime: Long): List<UsageStatModel> {
        return usageStatsManager?.queryUsageStats(
            UsageStatsManager.INTERVAL_DAILY,
            startTime,
            endTime,
        )?.map { UsageStatModel(it.packageName, it.totalTimeInForeground) } ?: emptyList()
    }
}
