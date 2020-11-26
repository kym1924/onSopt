package com.kimym.onsopt.data.api.dummy

class DummyRepository(private val requestInterface : DummyRequestInterface) {

    suspend fun getDummyUsers(page : Int) = requestInterface.getDummyUsers(page)
}
