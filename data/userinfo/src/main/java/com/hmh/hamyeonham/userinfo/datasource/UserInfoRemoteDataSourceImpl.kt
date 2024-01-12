package com.hmh.hamyeonham.userinfo.datasource

import android.util.Log
import com.hmh.hamyeonham.core.network.auth.datastore.HMHNetworkPreference
import com.hmh.hamyeonham.userinfo.mypage.MypageService
import com.hmh.hamyeonham.userinfo.model.UserInfoModel
import javax.inject.Inject
import kotlinx.coroutines.runBlocking

class UserInfoRemoteDataSourceImpl @Inject constructor(
    private val mypageService: MypageService,
    private val dataStore: HMHNetworkPreference
) : UserInfoRemoteDataSource {
    private val userInfoModel = UserInfoModel("", 0)
    override suspend fun getUserInfoModel(): UserInfoModel {
        val refreshToken = dataStore.refreshToken
        runCatching {
            runBlocking {
                mypageService.getUserInfo("Bearer $refreshToken")
            }
        }.onSuccess {
            val data = it.data
            return UserInfoModel(data.name, data.point)
        }.onFailure {
            Log.e("Authenticator", it.toString())
            runBlocking {
                // TODO 어떻게 처리할지 고민해보기
            }
        }.getOrThrow()
        return userInfoModel
    }
}
