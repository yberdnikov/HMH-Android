package com.hmh.hamyeonham.userinfo.datasource

import com.hmh.hamyeonham.userinfo.model.UserInfoResponse

interface UserInfoRemoteDataSource {
    suspend fun getUserInfoModel(): UserInfoResponse
}
