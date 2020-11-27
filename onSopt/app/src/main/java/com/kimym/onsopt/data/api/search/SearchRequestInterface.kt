package com.kimym.onsopt.data.api.search

import com.kimym.onsopt.data.model.WebData
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchRequestInterface {
    @Headers("Authorization: KakaoAK {REST-API KEY}")
    @GET("web")
    suspend fun getWebSearch(
        @Query("query") query : String,
        @Query("page") page : Int
    ) : WebData
}

