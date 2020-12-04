package com.kimym.onsopt.di

import com.kimym.onsopt.data.api.dummy.DummyDataSource
import com.kimym.onsopt.data.api.dummy.DummyDataSourceImpl
import com.kimym.onsopt.data.api.search.SearchDataSource
import com.kimym.onsopt.data.api.search.SearchDataSourceImpl
import com.kimym.onsopt.data.api.user.UserDataSource
import com.kimym.onsopt.data.api.user.UserDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class DataModuleBinds {

    @Binds
    abstract fun bindUserDataSource(userDataSourceImpl : UserDataSourceImpl) : UserDataSource

    @Binds
    abstract fun bindDummyDataSource(dummyDataSourceImpl : DummyDataSourceImpl) : DummyDataSource

    @Binds
    abstract fun bindSearchDataSource(searchDataSourceImpl : SearchDataSourceImpl) : SearchDataSource
}