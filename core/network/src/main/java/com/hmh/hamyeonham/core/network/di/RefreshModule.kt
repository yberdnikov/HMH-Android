package com.hmh.hamyeonham.core.network.di

import com.hmh.hamyeonham.common.qualifier.Unsecured
import com.hmh.hamyeonham.core.network.auth.api.RefreshService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RefreshModule {
    @Provides
    @Singleton
    fun provideRefreshApi(@Unsecured retrofit: Retrofit): RefreshService = retrofit.create()
}
