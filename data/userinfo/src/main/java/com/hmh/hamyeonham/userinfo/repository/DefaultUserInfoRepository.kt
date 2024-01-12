package com.hmh.hamyeonham.userinfo.repository

import com.hmh.hamyeonham.core.network.mypage.datasource.UserInfoDataSource
import com.hmh.hamyeonham.userinfo.model.UserInfo
import javax.inject.Inject

class DefaultUserInfoRepository @Inject constructor(
    private val userInfoDataSource: com.hmh.hamyeonham.core.network.mypage.datasource.UserInfoDataSource
) : UserInfoRepository {
    override fun getUserInfo(): UserInfo {
        val userInfoModel = userInfoDataSource.getUserInfoModel()
        return UserInfo(userInfoModel.name, userInfoModel.point)
    }
}
