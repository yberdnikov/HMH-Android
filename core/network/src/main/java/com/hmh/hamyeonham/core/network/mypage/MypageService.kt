package com.hmh.hamyeonham.core.network.mypage

import com.hmh.hamyeonham.core.network.model.BaseResponse
import com.hmh.hamyeonham.core.network.mypage.model.UserInfoResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface MypageService {
    @GET("api/v1/user")
    suspend fun getUserInfo(@Header("Authorization") token: String): BaseResponse<UserInfoResponse>
}
