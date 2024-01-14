package com.hmh.hamyeonham.core.database.di

import com.hmh.hamyeonham.core.database.HMHRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    @Singleton
    fun providesUsageGoalsDao(
        database: HMHRoomDatabase,
    ) = database.usageGoalsDao()
}
