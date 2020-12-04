package com.kimym.onsopt.data.api.dummy

import javax.inject.Inject

class DummyRepository @Inject constructor(
    private val dummyDataSource : DummyDataSource
) {
    suspend fun getDummyUsers(page : Int) = dummyDataSource.getDummyUsers(page)
}
