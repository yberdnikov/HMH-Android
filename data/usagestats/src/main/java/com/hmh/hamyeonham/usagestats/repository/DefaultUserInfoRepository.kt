package com.hmh.hamyeonham.usagestats.repository

import com.hmh.hamyeonham.usagestats.datasource.UserInfoDataSource
import com.hmh.hamyeonham.usagestats.model.UserInfo
import javax.inject.Inject

class DefaultUserInfoRepository @Inject constructor(
    private val userInfoDataSource: UserInfoDataSource,
) : UserInfoRepository {
    override fun getUserInfo(): UserInfo {
        val userInfoModel = userInfoDataSource.getUserInfoModel()
        return UserInfo(userInfoModel.name, userInfoModel.point)
    }
}
