package com.hmh.hamyeonham.feature.onboarding.model

import com.hmh.hamyeonham.login.model.SignRequestDomain

data class OnboardingAnswer(
    val usuallyUseTime: String = "",
    val problems: List<String> = emptyList(),
    val period: Int = -1,
    val goalTime: Int = 1,
    val apps: List<App> = emptyList(),
) {
    data class App(
        val appCode: String = "",
        val goalTime: Long = -1,
    )
}

fun OnboardingAnswer.toSignUpRequest(): SignRequestDomain {
    return SignRequestDomain(
        challenge = SignRequestDomain.Challenge(
            apps = SignRequestDomain.Challenge.Apps(
                apps = apps.joinToString(",") { it.appCode },
                goalTime = apps.sumOf { it.goalTime },
            ),
            goalTime = goalTime.toLong(),
            period = period,
        ),
        onboarding = SignRequestDomain.Onboarding(
            averageUseTime = usuallyUseTime,
            problem = problems,
        ),
        socialPlatform = "KAKAO",
    )
}
