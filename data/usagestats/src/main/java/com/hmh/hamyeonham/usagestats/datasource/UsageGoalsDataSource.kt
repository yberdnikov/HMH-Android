package com.hmh.hamyeonham.usagestats.datasource

import com.hmh.hamyeonham.usagestats.model.UsageGoalModel
import javax.inject.Inject

class UsageGoalsDataSource
    @Inject
    constructor() {
        var usageGoalList =
            listOf(
                UsageGoalModel("total", 10000000),
                UsageGoalModel("com.kakao.talk", 1000000),
                UsageGoalModel("com.google.android.gms", 500000),
                UsageGoalModel("com.google.android.youtube", 3000000),
                UsageGoalModel("com.android.chrome", 1000000),
            )

        fun getUsageGoals(): List<UsageGoalModel> {
            return usageGoalList
        }
    }
