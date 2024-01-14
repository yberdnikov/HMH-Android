package com.hmh.hamyeonham.usagestats.repository

import com.hmh.hamyeonham.usagestats.model.UsageGoal

interface UsageGoalsRepository {
    fun getUsageGoalTime(packageName: String): Long
    fun addUsageGoal(usageGoal: UsageGoal)
    suspend fun getUsageGoals(): List<UsageGoal>
}
