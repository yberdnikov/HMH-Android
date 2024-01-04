package com.hmh.hamyeonham.usagestats.usecase

import com.hmh.hamyeonham.usagestats.model.UsageStatAndGoal
import com.hmh.hamyeonham.usagestats.repository.UsageGoalsRepository
import com.hmh.hamyeonham.usagestats.repository.UsageStatsRepository
import javax.inject.Inject

class StaticsUseCase @Inject constructor(
    private val usageStatsRepository: UsageStatsRepository,
    private val usageGoalsRepository: UsageGoalsRepository
) {
    fun getUsageStatsAndGoals(
        startTime: Long,
        endTime: Long,
    ): List<UsageStatAndGoal> {
        val totalUsage = getTotalUsage(startTime, endTime)
        val usageForSelectedApps = getUsageStatsAndGoalsForSelectedApps(startTime, endTime)
        val totalUsageStatAndGoal =
            UsageStatAndGoal("total", totalUsage, usageGoalsRepository.getUsageGoalTime("total"))
        return listOf(totalUsageStatAndGoal) + usageForSelectedApps
    }

    private fun getUsageStatsAndGoalsForSelectedApps(
        startTime: Long,
        endTime: Long,
    ): List<UsageStatAndGoal> {
        val appList = getSelectedAppList()
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
    ): Long = usageStatsRepository.getUsageStats(startTime, endTime).sumOf {
        it.totalTimeInForeground
    }

    private fun getSelectedAppList(): List<String> =
        usageGoalsRepository.getUsageGoals().map { it.packageName }
}
