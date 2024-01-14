package com.hmh.hamyeonham.usagestats.model

data class UsageStatusAndGoal(
    val packageName: String,
    val totalTimeInForeground: Long,
    val goalTime: Long,
) {
    private val challengeSuccess: Boolean = (goalTime - totalTimeInForeground) >= 0
    val timeLeft: Long by lazy {
        if (challengeSuccess) goalTime - totalTimeInForeground else 0L
    }
    val usedPercentage: Int by lazy {
        if (challengeSuccess) (totalTimeInForeground * 100 / goalTime).toInt() else 100
    }
}
