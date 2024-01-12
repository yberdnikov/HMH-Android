package com.hmh.hamyeonham.usagestats.usecase

import com.hmh.hamyeonham.usagestats.model.UsageStatAndGoal
import com.hmh.hamyeonham.usagestats.repository.UsageGoalsRepository
import com.hmh.hamyeonham.usagestats.repository.UsageStatsRepository
import javax.inject.Inject

class GetUsageStatsListUseCase @Inject constructor(
    private val usageStatsRepository: UsageStatsRepository,
    private val usageGoalsRepository: UsageGoalsRepository
) {

    companion object {
        private const val TOTAL = "total"
    }

    suspend operator fun invoke(
        startTime: Long,
        endTime: Long
    ): List<UsageStatAndGoal> {
        val usageForSelectedApps = getUsageStatsAndGoalsForSelectedApps(startTime, endTime)
        val totalUsage = getTotalUsage(usageForSelectedApps)
        val totalUsageStatAndGoal =
            UsageStatAndGoal(TOTAL, totalUsage, usageGoalsRepository.getUsageGoalTime(TOTAL))
        return listOf(totalUsageStatAndGoal) + usageForSelectedApps
    }

    private suspend fun getUsageStatsAndGoalsForSelectedApps(
        startTime: Long,
        endTime: Long
    ): List<UsageStatAndGoal> {
        val appList = getSelectedPackageList()
        return usageStatsRepository.getUsageStatForPackages(startTime, endTime, appList)
            .map {
                createUsageStatAndGoal(
                    it.packageName,
                    it.totalTimeInForeground,
                    it.packageName
                )
            }
    }

    private fun getTotalUsage(
        usageStatAndGoalList: List<UsageStatAndGoal>
    ): Long {
        return usageStatAndGoalList.sumOf {
            it.totalTimeInForeground
        }
    }

    private suspend fun getSelectedPackageList(): List<String> =
        usageGoalsRepository.getUsageGoals().filter { it.packageName != TOTAL }
            .map { it.packageName }.distinct()

    private suspend fun createUsageStatAndGoal(
        packageName: String,
        totalTimeInForeground: Long,
        goalKey: String
    ): UsageStatAndGoal {
        val goalTime = usageGoalsRepository.getUsageGoalTime(goalKey)
        return UsageStatAndGoal(packageName, totalTimeInForeground, goalTime)
    }
}
