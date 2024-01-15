package com.hmh.hamyeonham.usagestats.repository

import com.hmh.hamyeonham.usagestats.model.UsageGoal

interface UsageGoalsRepository {
    fun getUsageGoalTimeFromMockData(packageName: String): Long
    fun addUsageGoal(usageGoal: UsageGoal)
    suspend fun getUsageGoals(): Result<List<UsageGoal>>
}
