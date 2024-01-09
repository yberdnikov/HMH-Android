package com.hmh.hamyeonham.usagestats.repository

import com.hmh.hamyeonham.usagestats.model.UserInfo

interface UserInfoRepository {
    fun getUserInfo(): UserInfo
}
