package com.kimym.onsopt.data.api

import com.kimym.onsopt.data.model.DummyUsers
import retrofit2.http.GET
import retrofit2.http.Query

interface DummyRequestInterface {
    @GET("users")
    fun getDummyUsers(
        @Query("page") page : String
    ) : DummyUsers
}