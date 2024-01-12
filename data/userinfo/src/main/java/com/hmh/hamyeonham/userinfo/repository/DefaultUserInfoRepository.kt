package com.hmh.hamyeonham.userinfo.repository

import com.hmh.hamyeonham.userinfo.datasource.UserInfoNetwork
import com.hmh.hamyeonham.userinfo.model.UserInfo
import com.hmh.hamyeonham.userinfo.model.UserInfoResponse
import javax.inject.Inject

class DefaultUserInfoRepository @Inject constructor(
    private val userInfoNetwork: UserInfoNetwork
) : UserInfoRepository {
    override fun getUserInfo(): Result<UserInfo> {
        val userInfoModel = userInfoNetwork.getUserInfoModel()
        return UserInfo(userInfoModel.name, userInfoModel.point)
    }
}
