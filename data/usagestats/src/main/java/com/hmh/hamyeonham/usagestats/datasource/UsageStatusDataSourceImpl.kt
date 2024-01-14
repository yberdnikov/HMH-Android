package com.hmh.hamyeonham.usagestats.datasource

import android.app.usage.UsageStatsManager
import com.hmh.hamyeonham.usagestats.model.UsageStatsModel
import javax.inject.Inject

class UsageStatusDataSourceImpl @Inject constructor(
    private val usageStatsManager: UsageStatsManager?,
) : UsageStatusDataSource {

    override fun getUsageStats(startTime: Long, endTime: Long): List<UsageStatsModel> {
        return usageStatsManager?.queryUsageStats(
            UsageStatsManager.INTERVAL_DAILY,
            startTime,
            endTime,
        )?.map { UsageStatsModel(it.packageName, it.totalTimeInForeground) } ?: emptyList()
    }
}
