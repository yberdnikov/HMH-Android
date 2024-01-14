package com.hmh.hamyeonham.userinfo.repository

import com.hmh.hamyeonham.core.network.mypage.datasource.MypageService
import com.hmh.hamyeonham.login.mapper.toUserInfo
import com.hmh.hamyeonham.userinfo.model.UserInfo
import javax.inject.Inject

class DefaultUserInfoRepository @Inject constructor(
    private val myPageService: MypageService
) : UserInfoRepository {
    override suspend fun getUserInfo(): Result<UserInfo> {
        return runCatching {
            myPageService.getUserInfo().data.toUserInfo()
        }
    }
}
