package com.hmh.hamyeonham.core.network.challenge.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChallengeRequest(
    @SerialName("goalTime")
    val goalTime: Int? = null,
    @SerialName("period")
    val period: Int? = null
)
