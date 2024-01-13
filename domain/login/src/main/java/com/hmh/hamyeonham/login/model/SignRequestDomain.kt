package com.hmh.hamyeonham.login.model

data class SignRequestDomain(
    val challenge: Challenge,
    val onboarding: Onboarding,
    val socialPlatform: String,
)

data class Onboarding(
    val averageUseTime: String,
    val problem: List<String>,
)

data class Challenge(
    val apps: Apps,
    val goalTime: Long,
    val period: Int,
)

data class Apps(
    val apps: String,
    val goalTime: Long,
)
