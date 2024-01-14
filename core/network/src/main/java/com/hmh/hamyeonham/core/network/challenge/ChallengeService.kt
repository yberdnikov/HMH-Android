package com.hmh.hamyeonham.core.network.challenge

import com.hmh.hamyeonham.core.network.challenge.model.ChallengeResponse
import com.hmh.hamyeonham.core.network.model.BaseResponse
import retrofit2.http.GET

interface ChallengeService {
    @GET("api/v1/challenge")
    suspend fun getChallengeData(): BaseResponse<ChallengeResponse>
}
