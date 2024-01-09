package com.hmh.hamyeonham.userinfo.di

import android.app.usage.UsageStatsManager
import android.content.Context
import com.hmh.hamyeonham.userinfo.model.UserInfoModel
import com.hmh.hamyeonham.userinfo.repository.DefaultUserInfoRepository
import com.hmh.hamyeonham.userinfo.repository.UserInfoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserInfoModule {
    @Provides
    @Singleton
    fun provideUserInfo(): UserInfoModel {
        return UserInfoModel
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface Binder {
        @Binds
        @Singleton
        fun provideUserInfoRepository(
            userInfoRepository: DefaultUserInfoRepository,
        ): UserInfoRepository
    }
}
