package com.kimym.onsopt.data.api

class DummyRepository(private val requestInterface : DummyRequestInterface) {

    suspend fun getDummyUsers() = requestInterface.getDummyUsers()
}
