package com.hmh.hamyeonham.core.network.challenge

import com.hmh.hamyeonham.core.network.challenge.model.ChallengeRequest
import com.hmh.hamyeonham.core.network.challenge.model.ChallengeResponse
import com.hmh.hamyeonham.core.network.model.BaseResponse
import retrofit2.http.GET
import retrofit2.http.POST

interface ChallengeService {
    @GET("api/v1/challenge")
    suspend fun getChallenge(): BaseResponse<ChallengeResponse>

    @POST("api/v1/challenge")
    suspend fun postChallenge(challengeRequest: ChallengeRequest): BaseResponse<ChallengeResponse>
}
