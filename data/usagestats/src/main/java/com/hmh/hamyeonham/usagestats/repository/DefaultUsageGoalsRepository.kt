package com.hmh.hamyeonham.usagestats.repository

import com.hmh.hamyeonham.usagestats.datasource.UsageGoalsRemoteDataSource
import com.hmh.hamyeonham.usagestats.model.UsageGoal
import javax.inject.Inject

class DefaultUsageGoalsRepository @Inject constructor(
    private val usageGoalsRemoteDataSource: UsageGoalsRemoteDataSource
) : UsageGoalsRepository {
    override fun getUsageGoals(): List<UsageGoal> {
        return usageGoalsRemoteDataSource.getUsageGoals().map {
            UsageGoal(it.packageName, it.goalTime)
        }
    }

    override fun getUsageGoalTime(packageName: String): Long {
        return usageGoalsRemoteDataSource.getUsageGoals()
            .firstOrNull { it.packageName == packageName }?.goalTime ?: 0
    }
}
