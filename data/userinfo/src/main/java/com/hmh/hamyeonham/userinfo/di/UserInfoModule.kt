package com.hmh.hamyeonham.userinfo.di

import com.hmh.hamyeonham.userinfo.datasource.UserInfoRemoteDataSource
import com.hmh.hamyeonham.userinfo.datasource.UserInfoNetwork
import com.hmh.hamyeonham.userinfo.repository.DefaultUserInfoRepository
import com.hmh.hamyeonham.userinfo.repository.UserInfoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserInfoModule {
    @Module
    @InstallIn(SingletonComponent::class)
    interface UserInfoBinder {
        @Binds @Singleton
        fun provideUserInfoDataSource(
            userInfoRemoteDataSourceImpl: UserInfoNetwork
        ): UserInfoRemoteDataSource

        @Binds @Singleton
        fun provideUserInfoRepository(
            userInfoRepository: DefaultUserInfoRepository
        ): UserInfoRepository
    }
}
