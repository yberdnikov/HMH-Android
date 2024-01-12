package com.hmh.hamyeonham.core.network.mypage.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoResponse(
    @SerialName("name")
    val name: String? = null,
    @SerialName("point")
    val point: Int? = null
)
