package com.kimym.onsopt.data.api.dummy

import javax.inject.Inject

class DummyDataSourceImpl @Inject constructor(
    private val dummyRequestInterface : DummyRequestInterface
) : DummyDataSource {
    override suspend fun getDummyUsers(page: Int) = dummyRequestInterface.getDummyUsers(page)
}