package com.kimym.onsopt.data.api

import com.kimym.onsopt.data.model.DummyUsers
import retrofit2.http.GET

interface DummyRequestInterface {
    @GET("users")
    suspend fun getDummyUsers(
    ) : DummyUsers
}