package com.hmh.hamyeonham.usagestats.repository

import com.hmh.hamyeonham.usagestats.model.UsageGoal

interface UsageGoalsRepository {
    fun getUsageGoalsForApps(): List<UsageGoal>

    fun getTotalUsageGoal(): UsageGoal

    fun getUsageGoalTime(packageName: String): Long
}
