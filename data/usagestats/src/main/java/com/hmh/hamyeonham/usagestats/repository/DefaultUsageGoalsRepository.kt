package com.hmh.hamyeonham.usagestats.repository

import com.hmh.hamyeonham.usagestats.datasource.UsageGoalsDataSource
import com.hmh.hamyeonham.usagestats.model.UsageGoal
import javax.inject.Inject

class DefaultUsageGoalsRepository
    @Inject
    constructor(
        private val usageGoalsDataSource: UsageGoalsDataSource,
    ) : UsageGoalsRepository {
        override fun getUsageGoals(): List<UsageGoal> {
            return usageGoalsDataSource.getUsageGoals().map { it ->
                UsageGoal(it.packageName, it.goalTime)
            }
        }

        override fun getUsageGoalTime(packageName: String): Long {
            return usageGoalsDataSource.getUsageGoals()
                .firstOrNull { it.packageName == packageName }?.goalTime ?: 0
        }
    }
