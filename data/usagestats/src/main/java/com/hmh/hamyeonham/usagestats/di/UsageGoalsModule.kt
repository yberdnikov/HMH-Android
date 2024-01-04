package com.hmh.hamyeonham.usagestats.di

import com.hmh.hamyeonham.usagestats.repository.DefaultUsageGoalsRepository
import com.hmh.hamyeonham.usagestats.repository.UsageGoalsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface Binder {
    @Binds
    @Singleton
    fun provideUsageGoalsRepository(usageGoalsRepository: DefaultUsageGoalsRepository): UsageGoalsRepository
}
