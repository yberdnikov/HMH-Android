package com.hmh.hamyeonham.challenge.model

data class ChallengeStatus(
    val appGoals: List<AppGoal> = emptyList(),
    val isSuccessList: List<Boolean?> = emptyList(),
    val goalTime: Int = 0,
    val period: Int = 0
) {
    data class AppGoal(
        val appCode: String,
        val appGoalTime: Int
    )
}
