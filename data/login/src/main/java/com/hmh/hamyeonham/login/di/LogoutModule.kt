package com.hmh.hamyeonham.login.di

import com.hmh.hamyeonham.login.DefaultLoginRepository
import com.hmh.hamyeonham.login.DefaultLogoutRepository
import com.hmh.hamyeonham.login.repository.LoginRepository
import com.hmh.hamyeonham.login.repository.LogoutRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object LogoutModule {
    @Module
    @InstallIn(SingletonComponent::class)
    interface Binder {
        @Binds
        @Singleton
        fun provideLogoutApi(loginRepository: DefaultLogoutRepository): LogoutRepository
    }
}