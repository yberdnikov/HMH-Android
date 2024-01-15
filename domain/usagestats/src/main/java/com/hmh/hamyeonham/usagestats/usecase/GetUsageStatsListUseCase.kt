package com.hmh.hamyeonham.usagestats.usecase

import com.hmh.hamyeonham.usagestats.model.UsageGoal
import com.hmh.hamyeonham.usagestats.model.UsageStatusAndGoal
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
    ): List<UsageStatusAndGoal> {
        val usageGoalsForSelectedPackages = usageGoalsRepository.getUsageGoals()
        val usageForSelectedApps =
            getUsageStatsAndGoalsForSelectedPackages(
                startTime,
                endTime,
                usageGoalsForSelectedPackages.filter { it.packageName != TOTAL },
            )
        val totalUsage = getTotalUsage(usageForSelectedApps)
        val totalUsageStatusAndGoal =
            UsageStatusAndGoal(
                TOTAL,
                totalUsage,
                getUsageGoalForPackage(usageGoalsForSelectedPackages, TOTAL),
            )
        return listOf(totalUsageStatusAndGoal) + usageForSelectedApps
    }

    private fun getUsageGoalForPackage(
        usageGoalsForSelectedPackages: List<UsageGoal>,
        packageName: String,
    ): Long {
        usageGoalsForSelectedPackages.forEach {
            if (it.packageName == packageName) {
                return it.goalTime
            }
        }
        return 0
    }

    private suspend fun getUsageStatsAndGoalsForSelectedPackages(
        startTime: Long,
        endTime: Long,
        usageGoalList: List<UsageGoal>,
    ): List<UsageStatusAndGoal> {
        val selectedPackage = getSelectedPackageList(usageGoalList)
        return usageStatsRepository.getUsageStatForPackages(startTime, endTime, selectedPackage)
            .map {
                createUsageStatAndGoal(
                    it.packageName,
                    it.totalTimeInForeground,
                    getUsageGoalForPackage(usageGoalList, it.packageName),
                )
            }
    }

    private fun getTotalUsage(
        usageStatusAndGoalList: List<UsageStatusAndGoal>,
    ): Long {
        return usageStatusAndGoalList.sumOf {
            it.totalTimeInForeground
        }
    }

    private fun getSelectedPackageList(usageGoalList: List<UsageGoal>): List<String> =
        usageGoalList.filter { it.packageName != TOTAL }
            .map { it.packageName }.distinct()

    private fun createUsageStatAndGoal(
        packageName: String,
        totalTimeInForeground: Long,
        goalTime: Long,
    ): UsageStatusAndGoal {
        return UsageStatusAndGoal(packageName, totalTimeInForeground, goalTime)
    }
}
