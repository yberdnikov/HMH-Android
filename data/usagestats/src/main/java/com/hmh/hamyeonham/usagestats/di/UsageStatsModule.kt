package com.hmh.hamyeonham.usagestats.di

import android.app.usage.UsageStatsManager
import android.content.Context
import com.hmh.hamyeonham.usagestats.datasource.UsageGoalsRemoteDataSource
import com.hmh.hamyeonham.usagestats.datasource.UsageStatusDataSource
import com.hmh.hamyeonham.usagestats.datasource.UsageStatusDataSourceImpl
import com.hmh.hamyeonham.usagestats.repository.DefaultUsageGoalsRepository
import com.hmh.hamyeonham.usagestats.repository.DefaultUsageStatusRepository
import com.hmh.hamyeonham.usagestats.repository.UsageGoalsRepository
import com.hmh.hamyeonham.usagestats.repository.UsageStatusRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UsageStatsModule {

    @Provides
    @Singleton
    fun provideUsageStatusManager(@ApplicationContext context: Context): UsageStatsManager? {
        return context.getSystemService(Context.USAGE_STATS_SERVICE) as? UsageStatsManager
    }

    @Provides
    @Singleton
    fun provideUsageGoalsDataSource(): UsageGoalsRemoteDataSource {
        return UsageGoalsRemoteDataSource()
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface Binder {
        @Binds
        @Singleton
        fun provideUsageStatusDataSource(usageStatsDataSource: UsageStatusDataSourceImpl): UsageStatusDataSource

        @Binds
        @Singleton
        fun provideUsageStatusRepository(usageStatsRepository: DefaultUsageStatusRepository): UsageStatusRepository

        @Binds
        @Singleton
        fun provideUsageGoalsRepository(usageGoalsRepository: DefaultUsageGoalsRepository): UsageGoalsRepository
    }
}
