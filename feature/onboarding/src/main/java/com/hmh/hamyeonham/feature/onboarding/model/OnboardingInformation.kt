package com.hmh.hamyeonham.feature.onboarding.model

data class OnboardingInformation(
    val usuallyUseTime: String = "",
    val problems: List<Problem> = emptyList(),
    val challenge: List<Challenge> = emptyList(),
    val apps: List<App> = emptyList(),
) {
    data class Problem(
        val description1: String = "",
        val description2: String = "",
    )

    data class Challenge(
        val period: Int = -1,
        val goalTime: Int = -1,
    )

    data class App(
        val appCode: String = "",
        val goalTime: Int = -1,
    )
}
