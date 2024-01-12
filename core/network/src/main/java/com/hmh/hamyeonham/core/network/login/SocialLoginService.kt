package com.hmh.hamyeonham.core.network.login

import com.hmh.hamyeonham.core.network.login.model.SocialLoginRequest
import com.hmh.hamyeonham.core.network.login.model.SocialLoginResponse
import com.hmh.hamyeonham.core.network.model.BaseResponse
import retrofit2.http.POST

interface SocialLoginService {
    @POST("api/v1/user/login")
    suspend fun login(request: SocialLoginRequest): BaseResponse<SocialLoginResponse>
}
