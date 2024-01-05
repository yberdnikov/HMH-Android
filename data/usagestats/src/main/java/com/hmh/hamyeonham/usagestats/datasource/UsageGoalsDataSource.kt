package com.hmh.hamyeonham.usagestats.datasource

import com.hmh.hamyeonham.usagestats.model.UsageGoalModel
import javax.inject.Inject

class UsageGoalsDataSource
    @Inject
    constructor() {
        var usageGoalList =
            listOf(
                UsageGoalModel("total", 201519990),
                UsageGoalModel("com.kakao.talk", 15686 * 2),
                UsageGoalModel("com.google.android.gms", 7134),
                UsageGoalModel("com.google.android.youtube", 71349),
                UsageGoalModel("com.android.chrome", 39445),
            )

        fun getUsageGoals(): List<UsageGoalModel> {
            return usageGoalList
        }
    }
