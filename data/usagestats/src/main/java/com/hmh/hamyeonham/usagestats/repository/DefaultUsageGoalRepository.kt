package com.hmh.hamyeonham.usagestats.repository

import com.hmh.hamyeonham.usagestats.datasource.UsageGoalsDataSource
import com.hmh.hamyeonham.usagestats.model.UsageGoal
import javax.inject.Inject

class DefaultUsageGoalsRepository
    @Inject
    constructor(
        private val usageGoalDataSource: UsageGoalsDataSource,
    ) : UsageGoalsRepository {
        override fun getUsageGoals(): List<UsageGoal> {
            val usageGoalsList = usageGoalDataSource.getUsageGoals()
            return usageGoalsList.map { usageGoalModel ->
                UsageGoal(usageGoalModel.packageName, usageGoalModel.goalTime)
            }
        }

        override fun getUsageGoalTime(packageName: String): Long {
            val usageGoalsList = usageGoalDataSource.getUsageGoals()
            return usageGoalsList.firstOrNull { it.packageName == packageName }?.goalTime ?: 0
        }
    }
