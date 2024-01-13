package com.hmh.hamyeonham.login.model

data class SignRequestDomain(
    val usuallyUseTime: String,
    val onboarding: Onboarding,
    val challenge: Challenge,
) {
    data class Onboarding(
        val averageUseTime: String,
        val problem: List<String>,
    )

    data class Challenge(
        val period: Int,
        val goalTime: Long,
        val apps: Apps,
    )

    data class Apps(
        val appCode: String,
        val goalTime: Long,
    )
}
