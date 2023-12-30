package com.hmh.hamyeonham.usagestats.datasource

import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import javax.inject.Inject

class UsageStatsDataSourceImpl @Inject constructor(
    private val usageStatsManager: UsageStatsManager?
) : UsageStatsDataSource {

    override fun getUsageStats(startTime: Long, endTime: Long): List<UsageStatModel> {
        val usageStatsList = queryUsageStats(startTime, endTime)
        return usageStatsList?.map { UsageStatModel(it.packageName, it.totalTimeInForeground) }
            ?: emptyList()
    }

    override fun getUsageTimeForPackage(startTime: Long, endTime: Long, packageName: String): Long {
        val usageStatsList = queryUsageStats(startTime, endTime)
        return usageStatsList?.firstOrNull { it.packageName == packageName }?.totalTimeInForeground
            ?: 0
    }

    override fun getUsageTimeForPackages(
        startTime: Long,
        endTime: Long,
        vararg packageNames: String
    ): List<UsageStatModel> {
        val usageStatsList = queryUsageStats(startTime, endTime)
        return packageNames.map { packageName ->
            val totalUsageTime = usageStatsList
                ?.filter { it.packageName == packageName }
                ?.sumOf { it.totalTimeInForeground } ?: 0
            UsageStatModel(packageName, totalUsageTime)
        }
    }

    override fun getUsageTimeForPackages(
        startTime: Long,
        endTime: Long,
        packageNames: List<String>
    ): List<UsageStatModel> {
        val usageStatsList = queryUsageStats(startTime, endTime)
        return packageNames.map { packageName ->
            val totalUsageTime = usageStatsList
                ?.filter { it.packageName == packageName }
                ?.sumOf { it.totalTimeInForeground } ?: 0
            UsageStatModel(packageName, totalUsageTime)
        }
    }


    private fun queryUsageStats(startTime: Long, endTime: Long): List<UsageStats>? {
        return usageStatsManager?.queryUsageStats(
            UsageStatsManager.INTERVAL_DAILY,
            startTime,
            endTime
        )
    }
}

data class UsageStatModel(val packageName: String, val totalTimeInForeground: Long)
