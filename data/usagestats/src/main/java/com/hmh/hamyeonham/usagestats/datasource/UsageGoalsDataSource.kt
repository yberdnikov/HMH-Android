package com.hmh.hamyeonham.usagestats.datasource

import com.hmh.hamyeonham.usagestats.model.UsageGoalModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UsageGoalsDataSource {
    @Provides
    @Singleton
    fun getUsageGoals(): List<UsageGoalModel> {
        return listOf(
            UsageGoalModel("total", 201519990),
            UsageGoalModel("com.kakao.talk", 15686 * 2),
            UsageGoalModel("com.google.android.gms", 7134),
            UsageGoalModel("com.google.android.youtube", 71349),
            UsageGoalModel("com.android.chrome", 39445),
        )
    }
}
