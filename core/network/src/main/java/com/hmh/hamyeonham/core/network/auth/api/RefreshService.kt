package com.hmh.hamyeonham.core.network.auth.api

import com.hmh.hamyeonham.core.network.auth.api.model.TokenResponse
import com.hmh.hamyeonham.core.network.model.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface RefreshService {
    @GET("api/v1/user/reissue")
    suspend fun refreshToken(@Header("Authorization") token: String): BaseResponse<TokenResponse>
}
