package com.hmh.hamyeonham.usagestats.repository

import com.hmh.hamyeonham.usagestats.model.UsageGoal

interface UsageGoalsRepository {
    fun getUsageGoals(): List<UsageGoal>

    fun getUsageGoalTime(packageName: String): Long
}
