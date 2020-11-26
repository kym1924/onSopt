package com.kimym.onsopt.data.api.dummy

import com.kimym.onsopt.data.model.DummyUsers
import retrofit2.http.GET
import retrofit2.http.Query

interface DummyRequestInterface {
    @GET("users")
    suspend fun getDummyUsers(
        @Query("page") page : Int
    ) : DummyUsers
}