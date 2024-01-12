package com.hmh.hamyeonham.userinfo.repository

import com.hmh.hamyeonham.userinfo.model.UserInfo

interface UserInfoRepository {
    fun getUserInfo(): Result<UserInfo>
}
