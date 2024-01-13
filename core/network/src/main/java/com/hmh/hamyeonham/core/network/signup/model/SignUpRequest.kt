package com.hmh.hamyeonham.core.network.signup.model

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
)

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
)

@Serializable
data class Apps(
    @SerialName("appCode")
    val apps: String,
    @SerialName("goalTime")
    val goalTime: Long,
)
