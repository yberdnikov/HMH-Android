package com.hmh.hamyeonham.userinfo.datasource

import com.hmh.hamyeonham.core.network.auth.datastore.HMHNetworkPreference
import com.hmh.hamyeonham.userinfo.model.UserInfoResponse
import com.hmh.hamyeonham.userinfo.mypage.MypageService
import javax.inject.Inject

class UserInfoNetwork @Inject constructor(
    private val mypageService: MypageService,
    private val dataStore: HMHNetworkPreference
) : UserInfoRemoteDataSource {
    override suspend fun getUserInfoModel(): UserInfoResponse {
        TODO("Not yet implemented")
    }
}
