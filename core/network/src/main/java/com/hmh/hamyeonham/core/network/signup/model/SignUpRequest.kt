package com.hmh.hamyeonham.core.network.signup.model

import com.hmh.hamyeonham.login.model.SignRequestDomain
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequest(
    @SerialName("challenge")
    val challenge: Challenge,
    @SerialName("onboarding")
    val onboarding: Onboarding,
    @SerialName("socialPlatform")
    val socialPlatform: String,
) {
    @Serializable
    data class Onboarding(
        @SerialName("averageUseTime")
        val averageUseTime: String,
        @SerialName("problem")
        val problem: List<String>,
    )

    @Serializable
    data class Challenge(
        @SerialName("apps")
        val apps: Apps,
        @SerialName("goalTime")
        val goalTime: Long,
        @SerialName("period")
        val period: Int,
    ) {
        @Serializable
        data class Apps(
            @SerialName("appCode")
            val apps: String,
            @SerialName("goalTime")
            val goalTime: Long,
        )
    }
}


fun SignUpRequest.toSignUpRequestDomain(): SignRequestDomain {
    return SignRequestDomain(
        challenge = SignRequestDomain.Challenge(
            apps = SignRequestDomain.Challenge.Apps(
                apps = challenge.apps.apps,
                goalTime = challenge.apps.goalTime,
            ),
            goalTime = challenge.goalTime,
            period = challenge.period,
        ),
        onboarding = SignRequestDomain.Onboarding(
            averageUseTime = onboarding.averageUseTime,
            problem = onboarding.problem,
        ),
        socialPlatform = socialPlatform,
    )
}

fun SignRequestDomain.toSignUpRequest(): SignUpRequest {
    return SignUpRequest(
        challenge = SignUpRequest.Challenge(
            apps = SignUpRequest.Challenge.Apps(
                apps = challenge.apps.apps,
                goalTime = challenge.apps.goalTime,
            ),
            goalTime = challenge.goalTime,
            period = challenge.period,
        ),
        onboarding = SignUpRequest.Onboarding(
            averageUseTime = onboarding.averageUseTime,
            problem = onboarding.problem,
        ),
        socialPlatform = socialPlatform,
    )
}
