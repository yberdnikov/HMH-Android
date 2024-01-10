package com.hmh.hamyeonham.usagestats.model

data class UsageGoal(
    val packageName: String = "",
    val goalTime: Long = 0,
) {

    val goalTimeInHours: Long
        get() = goalTime / 1000 / 60 / 60
    val goalTimeInMinutes: Long
        get() = goalTime / 1000 / 60

    val formattedGoalTime: String
        get() {
            val hours = goalTimeInHours
            val minutes = goalTimeInMinutes
            return buildString {
                if (hours > 0) {
                    append("$hours 시간")
                }
                if (minutes > 0) {
                    if (hours > 0) append(" ")
                    append("$minutes 분")
                }
            }
        }
}
