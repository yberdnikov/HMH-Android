package com.hmh.hamyeonham.data.challenge.di

import com.hmh.hamyeonham.challenge.repository.ChallengeRepository
import com.hmh.hamyeonham.data.challenge.repository.DefaultChallengeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChallengeModule {
    @Module
    @InstallIn(SingletonComponent::class)
    interface Binder {
        @Binds @Singleton
        fun provideChallengeRepository(challengeRepository: DefaultChallengeRepository): ChallengeRepository
    }
}
