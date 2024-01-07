package com.hmh.hamyeonham.di

import com.hmh.hamyeonham.common.navigation.NavigationProvider
import com.hmh.hamyeonham.navigation.DefaultNavigationProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigatorModule {
    @Binds
    @Singleton
    fun bindNavigator(navigator: DefaultNavigationProvider): NavigationProvider
}
