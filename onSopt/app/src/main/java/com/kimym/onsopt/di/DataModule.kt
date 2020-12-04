package com.kimym.onsopt.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.kimym.onsopt.data.api.RetrofitBuilder
import com.kimym.onsopt.data.api.dummy.DummyDataSource
import com.kimym.onsopt.data.api.dummy.DummyRepository
import com.kimym.onsopt.data.api.dummy.DummyRequestInterface
import com.kimym.onsopt.data.api.search.SearchDataSource
import com.kimym.onsopt.data.api.search.SearchRepository
import com.kimym.onsopt.data.api.search.SearchRequestInterface
import com.kimym.onsopt.data.api.user.UserDataSource
import com.kimym.onsopt.data.api.user.UserRepository
import com.kimym.onsopt.data.api.user.UserRequestInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DataModule {


    @Provides
    @Singleton
    fun provideSharedPreferences(application: Application): SharedPreferences =
        application.getSharedPreferences("pref", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideUserRequestInterface(): UserRequestInterface = RetrofitBuilder.userService

    @Provides
    @Singleton
    fun provideUserRepository(userDataSourceImpl : UserDataSource, sharedPreferences : SharedPreferences) =
        UserRepository(userDataSourceImpl, sharedPreferences)

    @Provides
    @Singleton
    fun provideDummyRequestInterface(): DummyRequestInterface = RetrofitBuilder.dummyService

    @Provides
    @Singleton
    fun provideDummyRepository(dummyDataSourceImpl : DummyDataSource) =
        DummyRepository(dummyDataSourceImpl)

    @Provides
    @Singleton
    fun provideSearchRequestInterface(): SearchRequestInterface = RetrofitBuilder.searchService

    @Provides
    @Singleton
    fun provideSearchRepository(searchDataSourceImpl : SearchDataSource) =
        SearchRepository(searchDataSourceImpl)
}