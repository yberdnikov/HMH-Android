package com.hmh.hamyeonham.core.network.signup.model

import com.hmh.hamyeonham.login.model.SignUpUser
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponse(
    @SerialName("accessToken")
    val accessToken: String,
    @SerialName("refreshToken")
    val refreshToken: String,
    @SerialName("userId")
    val userId: Int,
) {
    fun toSignUpUser(): SignUpUser {
        return SignUpUser(
            userId = userId,
            accessToken = accessToken,
            refreshToken = refreshToken,
        )
    }
}
