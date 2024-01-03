package com.hmh.hamyeonham.usagestats.usecase

import com.hmh.hamyeonham.usagestats.model.UsageStatAndGoal
import com.hmh.hamyeonham.usagestats.repository.UsageGoalsRepository
import com.hmh.hamyeonham.usagestats.repository.UsageStatsRepository
import javax.inject.Inject

class StaticsUseCase
    @Inject
    constructor(
        private val usageStatsRepository: UsageStatsRepository,
        private val usageGoalsRepository: UsageGoalsRepository,
    ) {
        fun getStatics(
            startTime: Long,
            endTime: Long,
        ): Pair<List<UsageStatAndGoal>, Long> {
            val appUsageStat =
                getAppUsageStat(startTime, endTime)
            val totalUsage = getTotalUsage(startTime, endTime)
            return Pair(appUsageStat, totalUsage)
        }

        private fun getAppUsageStat(
            startTime: Long,
            endTime: Long,
        ): List<UsageStatAndGoal> {
            val appList = getAppList()
            return usageStatsRepository.getUsageTimeForPackages(startTime, endTime, appList).map {
                UsageStatAndGoal(
                    it.packageName,
                    it.totalTimeInForeground,
                    usageGoalsRepository.getUsageGoalTime(it.packageName),
                )
            }
        }

        private fun getTotalUsage(
            startTime: Long,
            endTime: Long,
        ): Long =
            usageStatsRepository.getUsageStats(startTime, endTime)
                .sumOf { it.totalTimeInForeground }

        private fun getAppList(): List<String> = usageGoalsRepository.getUsageGoals().map { it.packageName }
    }
