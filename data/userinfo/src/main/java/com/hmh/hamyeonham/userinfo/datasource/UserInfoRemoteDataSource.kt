package com.hmh.hamyeonham.userinfo.datasource

import com.hmh.hamyeonham.userinfo.model.UserInfoModel

interface UserInfoRemoteDataSource {
    suspend fun getUserInfoModel(): UserInfoModel
}
