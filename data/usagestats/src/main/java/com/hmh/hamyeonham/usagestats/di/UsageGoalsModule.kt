package com.hmh.hamyeonham.usagestats.di

import com.hmh.hamyeonham.usagestats.model.UsageGoalModel
import com.hmh.hamyeonham.usagestats.repository.DefaultUsageGoalsRepository
import com.hmh.hamyeonham.usagestats.repository.UsageGoalsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UsageGoalsModule {
    @Provides
    @Singleton
    fun provideUsageGoalModels(): List<UsageGoalModel> {
        return listOf(
            UsageGoalModel("total", 15686 * 3),
            UsageGoalModel("com.kakao.talk", 15686 * 2),
            UsageGoalModel("com.google.android.gms", 7134),
            UsageGoalModel("com.google.android.youtube", 7134),
            UsageGoalModel("com.android.chrome", 39445),
        )
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface Binder {
        @Binds
        @Singleton
        fun provideUsageGoalsRepository(usageGoalsRepository: DefaultUsageGoalsRepository): UsageGoalsRepository
    }
}
