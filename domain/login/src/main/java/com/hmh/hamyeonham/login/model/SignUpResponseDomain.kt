package com.hmh.hamyeonham.login.model

data class SignUpResponseDomain(
    val userId: Int,
    val accessToken: String,
    val refreshToken: String,
)
