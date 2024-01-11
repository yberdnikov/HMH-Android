package com.hmh.hamyeonham.feature.onboarding.model

data class OnboardingAnswer(
    val usuallyUseTime: String = "",
    val problems: List<String> = emptyList(),
    val period: Int = -1,
    val goalTime: Int = -1,
    val apps: List<App> = emptyList(),
) {
    data class App(
        val appCode: String = "",
        val goalTime: Int = -1,
    )
}
