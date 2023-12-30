package com.hmh.hamyeonham.usagestats.datasource

interface UsageStatsDataSource {
    fun getUsageStats(startTime: Long, endTime: Long): List<UsageStatModel>
    fun getUsageTimeForPackage(startTime: Long, endTime: Long, packageName: String): Long
    fun getUsageTimeForPackages(
        startTime: Long,
        endTime: Long,
        vararg packageNames: String
    ): List<UsageStatModel>

    fun getUsageTimeForPackages(
        startTime: Long,
        endTime: Long,
        packageNames: List<String>
    ): List<UsageStatModel>
}
