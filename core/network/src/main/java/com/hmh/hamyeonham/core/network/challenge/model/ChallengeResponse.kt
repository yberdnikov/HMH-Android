package com.hmh.hamyeonham.core.network.challenge.model

import com.hmh.hamyeonham.core.network.usagegoal.model.UsageGoalResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("apps")
    val apps: List<UsageGoalResponse.AppGoal>,
    @SerialName("goalTime")
    val goalTime: Int,
    @SerialName("period")
    val period: Int,
    @SerialName("statuses")
    val statuses: List<String>,
    @SerialName("todayIndex")
    val todayIndex: Int
)
