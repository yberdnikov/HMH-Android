package com.hmh.hamyeonham.core.network.di

import com.hmh.hamyeonham.common.qualifier.Unsecured
import com.hmh.hamyeonham.core.network.auth.api.RefreshService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object UsageGoalModule {
    @Provides
    @Singleton
    fun provideUsageGoalApi(@Unsecured retrofit: Retrofit): RefreshService = retrofit.create()
}
