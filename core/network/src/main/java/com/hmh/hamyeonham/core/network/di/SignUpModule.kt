package com.hmh.hamyeonham.core.network.di

import com.hmh.hamyeonham.common.qualifier.Secured
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
    fun signUpApi(@Secured retrofit: Retrofit): SignUpModule = retrofit.create()
}
