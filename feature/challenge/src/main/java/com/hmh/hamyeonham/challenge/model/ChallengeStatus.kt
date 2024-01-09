package com.hmh.hamyeonham.challenge.model


data class ChallengeStatus(
    val apps: List<App>,
    val isSuccessList: List<Boolean>,
    val goalTime: Int,
    val period: Int,
) {
    data class App(
        val appCode: String,
        val appGoalTime: Int
    )
}
