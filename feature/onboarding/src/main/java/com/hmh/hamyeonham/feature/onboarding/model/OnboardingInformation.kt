package com.hmh.hamyeonham.feature.onboarding.model

data class OnboardingInformation(
    val usuallyUseTime: String = "",
    val onboarding: Onboarding = Onboarding(),
    val challenge: Challenge = Challenge(),
) {
    data class Onboarding(
        val averageUseTime: String = "",
        val problem: List<String> = listOf(),
    )

    data class Challenge(
        val period: Int = -1,
        val goalTime: Long = -1,
        val apps: Apps = Apps(),
    )

    data class Apps(
        val appCode: String = "",
        val goalTime: Long = -1,
    )
}
