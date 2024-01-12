package com.hmh.hamyeonham.core.network.login

import com.hmh.hamyeonham.core.network.login.model.LoginRequest
import com.hmh.hamyeonham.core.network.login.model.LoginResponse
import com.hmh.hamyeonham.core.network.model.BaseResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginService {
    @POST("api/v1/user/login")
    suspend fun login(
        @Header("Authorization") accessToken: String,
        @Body request: LoginRequest
    ): BaseResponse<LoginResponse>
}
