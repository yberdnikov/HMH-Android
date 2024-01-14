package com.hmh.hamyeonham.core.network.service

import com.hmh.hamyeonham.core.network.model.BaseResponse
import com.hmh.hamyeonham.core.network.signup.model.SignUpRequest
import com.hmh.hamyeonham.core.network.signup.model.SignUpResponse
import com.hmh.hamyeonham.login.model.SignRequestDomain
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface SignUpService {
    @POST("api/v1/user/signup")
    suspend fun signUp(
        @Header("Authorization") accessToken: String,
        @Header("OS") OS: String,
        @Body request: SignUpRequest,
    ): BaseResponse<SignUpResponse>
}
