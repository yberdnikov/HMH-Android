package com.hmh.hamyeonham.usagestats.repository

import com.hmh.hamyeonham.usagestats.model.UsageGoal
import com.hmh.hamyeonham.usagestats.model.UsageGoalModel
import javax.inject.Inject

class DefaultUsageGoalsRepository @Inject constructor(
    private val usageGoalList: List<UsageGoalModel>,
) : UsageGoalsRepository {
    override fun getUsageGoals(): List<UsageGoal> {
        return usageGoalList.map {
            UsageGoal(it.packageName, it.goalTime)
        }
    }

    override fun getUsageGoalTime(packageName: String): Long {
        return usageGoalList.firstOrNull { it.packageName == packageName }?.goalTime ?: 0
    }
}
