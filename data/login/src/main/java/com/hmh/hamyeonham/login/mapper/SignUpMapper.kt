package com.hmh.hamyeonham.login.mapper

import com.hmh.hamyeonham.core.network.signup.model.SignUpRequest
import com.hmh.hamyeonham.core.network.signup.model.SignUpResponse

internal fun SignUpResponse.toSignUp(): SignUpResponse {
    return SignUpResponse(
        userId = userId ?: -1,
        accessToken = accessToken.orEmpty(),
        refreshToken = refreshToken.orEmpty(),
    )
}

internal fun SignUpRequest.toSignRequest(): SignUpRequest {
    return SignUpRequest(

    )
}