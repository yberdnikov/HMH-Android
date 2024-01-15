package com.hmh.hamyeonham.usagestats.repository

import com.hmh.hamyeonham.core.network.usagegoal.UsageGoalService
import com.hmh.hamyeonham.login.mapper.toUsageGoalList
import com.hmh.hamyeonham.usagestats.datasource.UsageGoalsRemoteDataSource
import com.hmh.hamyeonham.usagestats.model.UsageGoal
import javax.inject.Inject

class DefaultUsageGoalsRepository @Inject constructor(
    private val usageGoalService: UsageGoalService,
    private val usageGoalsRemoteDataSource: UsageGoalsRemoteDataSource,
) : UsageGoalsRepository {

    override suspend fun getUsageGoals(): List<UsageGoal> {
        return usageGoalService.getUsageGoal().data.toUsageGoalList()
    }

    override fun getUsageGoalTime(packageName: String): Long {
        return usageGoalsRemoteDataSource.getUsageGoals()
            .firstOrNull { it.packageName == packageName }?.goalTime ?: 0
    }

    override fun addUsageGoal(usageGoal: UsageGoal) {
        usageGoalsRemoteDataSource.addUsageGoal(usageGoal)
    }
}
