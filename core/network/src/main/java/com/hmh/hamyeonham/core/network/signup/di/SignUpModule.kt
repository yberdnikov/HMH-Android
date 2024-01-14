package com.hmh.hamyeonham.core.network.signup.di

import com.hmh.hamyeonham.common.qualifier.Secured
import com.hmh.hamyeonham.common.qualifier.Unsecured
import com.hmh.hamyeonham.core.network.service.SignUpService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SignUpModule {
    @Provides
    @Singleton
    fun provideSignUpApi(@Unsecured retrofit: Retrofit): SignUpService = retrofit.create()
}
