package com.hmh.hamyeonham.core.network.SignUp

import com.hmh.hamyeonham.core.network.SignUp.model.SignUpRequest
import com.hmh.hamyeonham.core.network.SignUp.model.SignUpResponse
import com.hmh.hamyeonham.core.network.model.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("api/v1/user/signup")
    suspend fun signUp(
        @Body request: SignUpRequest,
    ): BaseResponse<SignUpResponse>
}
