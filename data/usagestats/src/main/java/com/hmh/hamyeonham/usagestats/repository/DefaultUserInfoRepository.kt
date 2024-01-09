package com.hmh.hamyeonham.usagestats.repository

import com.hmh.hamyeonham.usagestats.datasource.UserInfoDataSource
import com.hmh.hamyeonham.usagestats.model.UserInfo
import javax.inject.Inject

class DefaultUserInfoRepository @Inject constructor(
    userInfoDataSource: UserInfoDataSource,
) : UserInfoRepository {
    override fun getUserInfo(): UserInfo {
        TODO("Not yet implemented")
    }
}
