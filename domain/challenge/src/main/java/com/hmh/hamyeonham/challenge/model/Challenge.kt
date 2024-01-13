package com.hmh.hamyeonham.challenge.model

data class Challenge(
    val apps: List<App> = listOf(),
    val goalTime: Int = 0,
    val period: Int = 0,
) {

    data class App(
        val appCode: String,
        val appGoalTime: Int
    )
}
