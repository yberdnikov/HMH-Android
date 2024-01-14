package com.hmh.hamyeonham.core.network.usagegoal

import com.hmh.hamyeonham.core.network.model.BaseResponse
import com.hmh.hamyeonham.core.network.usagegoal.model.UsageGoalResponse
import retrofit2.http.GET

interface UsageGoalService {
    @GET("api/v1/dailychallenge")
    suspend fun getUsageGoal(): BaseResponse<UsageGoalResponse>
}
