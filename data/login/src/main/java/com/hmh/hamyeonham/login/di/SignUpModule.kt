package com.hmh.hamyeonham.login.di

import com.hmh.hamyeonham.login.DefaultSignUpRepository
import com.hmh.hamyeonham.login.repository.SignUpRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SignUpModule {
    @Module
    @InstallIn(SingletonComponent::class)
    interface Binder {
        @Binds
        @Singleton
        fun provideSignUpRepository(signRepository: DefaultSignUpRepository): SignUpRepository
    }
}
