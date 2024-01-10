package com.hmh.hamyeonham.core.network.auth.api

import com.hmh.hamyeonham.core.network.auth.api.model.TokenResponse
import retrofit2.http.Header
import retrofit2.http.POST

interface RefreshApi {
    @POST("api/v1/auth/token")
    suspend fun refreshToken(@Header("Authorization") token: String): TokenResponse
}
