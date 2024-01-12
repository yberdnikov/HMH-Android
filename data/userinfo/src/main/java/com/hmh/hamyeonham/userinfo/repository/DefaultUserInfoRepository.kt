package com.hmh.hamyeonham.userinfo.repository

import com.hmh.hamyeonham.userinfo.datasource.UserInfoNetwork
import com.hmh.hamyeonham.userinfo.model.UserInfo
import javax.inject.Inject

class DefaultUserInfoRepository @Inject constructor(
    private val userInfoNetwork: UserInfoNetwork
) : UserInfoRepository {
    override suspend fun getUserInfo(): Result<UserInfo> {
        return runCatching { userInfoNetwork.getUserInfoModel().toUserInfo() }
    }
}
