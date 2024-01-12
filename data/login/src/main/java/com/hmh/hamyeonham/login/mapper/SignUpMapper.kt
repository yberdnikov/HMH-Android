package com.hmh.hamyeonham.login.mapper

import com.hmh.hamyeonham.core.network.SignUp.model.SignUpResponse
import com.hmh.hamyeonham.login.model.Login

internal fun SignUpResponse.toSignUp(): Login {
    return Login(
        userId = userId ?: -1,
        refreshToken = refreshToken ?: "",
        accessToken = accessToken ?: "",
    )
}
