package com.hmh.hamyeonham.usagestats.usecase

import com.hmh.hamyeonham.usagestats.model.UsageGoal
import com.hmh.hamyeonham.usagestats.model.UsageStatAndGoal
import com.hmh.hamyeonham.usagestats.repository.UsageGoalsRepository
import com.hmh.hamyeonham.usagestats.repository.UsageStatsRepository
import javax.inject.Inject

class GetUsageStatsListUseCase @Inject constructor(
    private val usageStatsRepository: UsageStatsRepository,
    private val usageGoalsRepository: UsageGoalsRepository,
) {

    companion object {
        private const val TOTAL = "total"
    }

    suspend operator fun invoke(
        startTime: Long,
        endTime: Long,
    ): List<UsageStatAndGoal> {
        val usageGoalsForSelectedPackages = usageGoalsRepository.getUsageGoals()
        val usageForSelectedApps =
            getUsageStatsAndGoalsForSelectedPackages(
                startTime,
                endTime,
                usageGoalsForSelectedPackages,
            )
//        val totalUsage = getTotalUsage(usageForSelectedApps)
//        val totalUsageStatAndGoal =
//            UsageStatAndGoal(TOTAL, totalUsage, usageGoalsRepository.getUsageGoalTime(TOTAL))
        return usageForSelectedApps
    }

    private suspend fun getUsageStatsAndGoalsForSelectedPackages(
        startTime: Long,
        endTime: Long,
        usageGoalList: List<UsageGoal>,
    ): List<UsageStatAndGoal> {
        val selectedPackage = getSelectedPackageList(usageGoalList)
        return usageStatsRepository.getUsageStatForPackages(startTime, endTime, selectedPackage)
            .map {
                createUsageStatAndGoal(
                    it.packageName,
                    it.totalTimeInForeground,
                    it.packageName,
                )
            }
    }

    private fun getTotalUsage(
        usageStatAndGoalList: List<UsageStatAndGoal>,
    ): Long {
        return usageStatAndGoalList.sumOf {
            it.totalTimeInForeground
        }
    }

    private fun getSelectedPackageList(usageGoalList: List<UsageGoal>): List<String> =
        usageGoalList.filter { it.packageName != TOTAL }
            .map { it.packageName }.distinct()

    private suspend fun createUsageStatAndGoal(
        packageName: String,
        totalTimeInForeground: Long,
        goalKey: String,
    ): UsageStatAndGoal {
        val goalTime = usageGoalsRepository.getUsageGoalTime(goalKey)
        return UsageStatAndGoal(packageName, totalTimeInForeground, goalTime)
    }
}
