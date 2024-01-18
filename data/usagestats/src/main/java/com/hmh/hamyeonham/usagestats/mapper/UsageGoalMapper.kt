package com.hmh.hamyeonham.usagestats.mapper

import com.hmh.hamyeonham.core.network.usagegoal.model.UsageGoalResponse
import com.hmh.hamyeonham.usagestats.model.UsageGoal

private const val TOTAL = "total"
internal fun UsageGoalResponse.toUsageGoalList(): List<UsageGoal> {
    return listOf(UsageGoal(TOTAL, goalTime)) + apps.map {
        UsageGoal(it.appCode, it.goalTime)
    }
}
