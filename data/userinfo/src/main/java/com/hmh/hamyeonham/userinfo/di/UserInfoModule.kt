package com.hmh.hamyeonham.userinfo.di

import com.hmh.hamyeonham.userinfo.datasource.UserInfoDataSource
import com.hmh.hamyeonham.userinfo.model.UserInfoModel
import com.hmh.hamyeonham.userinfo.repository.DefaultUserInfoRepository
import com.hmh.hamyeonham.userinfo.repository.UserInfoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserInfoModule {

    @Provides
    @Singleton
    fun provideUserInfoDataSource(userInfoDataSource: UserInfoDataSource): UserInfoModel {
        return userInfoDataSource.getUserInfoModel()
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface UserInfoBinder {
        @Binds
        @Singleton
        fun provideUserInfoRepository(
            userInfoRepository: DefaultUserInfoRepository,
        ): UserInfoRepository
    }
}
