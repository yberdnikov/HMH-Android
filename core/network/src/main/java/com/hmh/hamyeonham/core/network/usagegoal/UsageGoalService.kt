package com.hmh.hamyeonham.core.network.usagegoal

import com.hmh.hamyeonham.core.network.auth.api.model.TokenResponse
import com.hmh.hamyeonham.core.network.model.BaseResponse
import com.hmh.hamyeonham.core.network.usagegoal.model.UsageGoalResponse
import retrofit2.http.GET

interface UsageGoalService {
    @GET("api/v1/dailyChallenge")
    suspend fun getUsageGoal(): BaseResponse<UsageGoalResponse>
}
