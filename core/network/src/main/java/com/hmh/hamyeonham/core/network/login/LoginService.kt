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

    @POST("api/v1/user/logout")
    suspend fun logout(
        @Header("Authorization") accessToken: String,
    ): BaseResponse<Unit>

    @POST("api/v1/user")
    suspend fun withdrawal(
        @Header("Authorization") accessToken: String,
    ): BaseResponse<Unit>
}

