package com.hmh.hamyeonham.usagestats.model

data class UsageStatAndGoal(
    val packageName: String,
    val totalTimeInForeground: Long,
    val goalTime: Long,
) {
    val challengeSuccess: Boolean = (goalTime - totalTimeInForeground > 0)
}
