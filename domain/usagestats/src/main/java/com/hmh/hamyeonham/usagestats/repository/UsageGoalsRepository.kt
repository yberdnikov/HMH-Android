package com.hmh.hamyeonham.usagestats.repository

import com.hmh.hamyeonham.usagestats.model.UsageGoal

interface UsageGoalsRepository {
    suspend fun getUsageGoals(): Result<List<UsageGoal>>
    suspend fun getUsageGoalTime(packageName: String): Long
}
