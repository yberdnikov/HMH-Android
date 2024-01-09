package com.hmh.hamyeonham.usagestats.datasource

import com.hmh.hamyeonham.usagestats.model.UsageStatModel

interface UsageStatsDataSource {
    fun getUsageStats(startTime: Long, endTime: Long): List<UsageStatModel>
}
