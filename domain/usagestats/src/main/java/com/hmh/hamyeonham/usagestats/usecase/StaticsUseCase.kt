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
        fun getUsageStatsAndGoals(
            startTime: Long,
            endTime: Long,
        ): List<UsageStatAndGoal> {
            return getUsageStatsAndGoalsForSelectedApps(startTime, endTime)
        }

        fun getTotalUsageStatsAndGoals(
            startTime: Long,
            endTime: Long,
        ): UsageStatAndGoal {
            val totalUsage =
                getTotalUsage(startTime, endTime)
            return UsageStatAndGoal("total", totalUsage, usageGoalsRepository.getUsageGoalTime("total"))
        }

        private fun getTotalUsage(
            startTime: Long,
            endTime: Long,
        ): Long =
            usageStatsRepository.getUsageStats(startTime, endTime)
                .sumOf { it.totalTimeInForeground }

        private fun getUsageStatsAndGoalsForSelectedApps(
            startTime: Long,
            endTime: Long,
        ): List<UsageStatAndGoal> {
            val selectedAppList = getSelectedAppList()
            val usageStatAndGoal =
                usageStatsRepository.getUsageTimeForPackages(startTime, endTime, selectedAppList).map {
                    UsageStatAndGoal(
                        it.packageName,
                        it.totalTimeInForeground,
                        usageGoalsRepository.getUsageGoalTime(it.packageName),
                    )
                }
            return usageStatAndGoal
        }

        private fun getSelectedAppList(): List<String> = usageGoalsRepository.getUsageGoalsForApps().map { it.packageName }
    }
