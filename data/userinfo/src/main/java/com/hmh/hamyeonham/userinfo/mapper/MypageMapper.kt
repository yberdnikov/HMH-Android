package com.hmh.hamyeonham.userinfo.mapper

internal fun UserInfoResponse.getUser(): Login {
    return Login(
        userId = userId ?: -1,
        accessToken = token?.accessToken.orEmpty(),
        refreshToken = token?.refreshToken.orEmpty(),
    )
}
