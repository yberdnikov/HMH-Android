package com.hmh.hamyeonham.usagestats.repository

import com.hmh.hamyeonham.core.network.usagegoal.UsageGoalService
import com.hmh.hamyeonham.login.mapper.toUsageGoalList
import com.hmh.hamyeonham.usagestats.model.UsageGoal
import javax.inject.Inject

class DefaultUsageGoalsRepository @Inject constructor(
    private val usageGoalService: UsageGoalService,
) : UsageGoalsRepository {
    override suspend fun getUsageGoals(): Result<List<UsageGoal>> {
        return runCatching { usageGoalService.getUsageGoal().data.toUsageGoalList() }
    }

    override suspend fun getUsageGoalTime(packageName: String): Long {
        return getUsageGoals()
            .firstOrNull { it.packageName == packageName }?.goalTime ?: 0
    }
}
