package com.hmh.hamyeonham.feature.onboarding.model

data class OnBoardingUserData(
    val onboarding: OnboardingData,
    val challenge: ChallengeData,
) {
    data class OnboardingData(
        val usuallyUseTime: String,
        val problem: String,
    )

    data class ChallengeData(
        val period: Int,
        val goalTime: Long,
        val apps: List<AppData>,
    )

    data class AppData(
        val appCode: String,
        val appGoalTime: Long,
    )
}
