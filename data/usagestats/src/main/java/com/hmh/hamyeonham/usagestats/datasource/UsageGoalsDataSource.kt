package com.hmh.hamyeonham.usagestats.datasource

import com.hmh.hamyeonham.usagestats.model.UsageGoalModel
import javax.inject.Inject

class UsageGoalsDataSource @Inject constructor() {
    fun getUsageGoals(): List<UsageGoalModel> {
        return listOf(
            UsageGoalModel("total", 9459489),
            UsageGoalModel("com.kakao.talk", 10737116),
            UsageGoalModel("com.google.android.gms", 10607821),
            UsageGoalModel("com.google.android.youtube", 7409658),
            UsageGoalModel("com.android.chrome", 9346527)
        )
    }
}
