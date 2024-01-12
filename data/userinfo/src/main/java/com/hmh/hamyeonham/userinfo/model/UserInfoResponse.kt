package com.hmh.hamyeonham.userinfo.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoResponse(
    @SerialName("name")
    val name: String = "",
    @SerialName("point")
    val point: Int = 0
)
