package com.hmh.hamyeonham.login.di

import com.hmh.hamyeonham.login.DefaultWithdrawalRepository
import com.hmh.hamyeonham.login.repository.WithdrawalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WithdrawalModule {
    @Module
    @InstallIn(SingletonComponent::class)
    interface Binder {
        @Binds
        @Singleton
        fun provideWithdrawalApi(withdrawalRepository: DefaultWithdrawalRepository): WithdrawalRepository
    }
}
