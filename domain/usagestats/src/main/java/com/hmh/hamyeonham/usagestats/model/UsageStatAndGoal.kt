package com.hmh.hamyeonham.usagestats.model

data class UsageStatAndGoal(
    val packageName: String,
    val totalTimeInForeground: Long,
    val goalTime: Long,
) {
    private val challengeSuccess: Boolean = (goalTime - totalTimeInForeground) >= 0
    val timeLeft by lazy {
        if (challengeSuccess) {
            goalTime - totalTimeInForeground
        } else {
            0
        }
    }
    val usedPercentage by lazy {
        if (challengeSuccess) {
            (totalTimeInForeground * 100 / goalTime).toInt()
        } else {
            100
        }
    }
}
