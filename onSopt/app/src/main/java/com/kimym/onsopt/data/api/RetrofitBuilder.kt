package com.kimym.onsopt.data.api

import com.kimym.onsopt.data.api.dummy.DummyRequestInterface
import com.kimym.onsopt.data.api.search.SearchRequestInterface
import com.kimym.onsopt.data.api.user.UserRequestInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private var userRetrofit : Retrofit = Retrofit.Builder().baseUrl("http://15.164.83.210:3000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var userService : UserRequestInterface = userRetrofit.create(
        UserRequestInterface::class.java)

    private var dummyRetrofit : Retrofit = Retrofit.Builder().baseUrl("https://reqres.in/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var dummyService : DummyRequestInterface = dummyRetrofit.create(DummyRequestInterface::class.java)

    private var searchRetrofit : Retrofit = Retrofit.Builder().baseUrl("https://dapi.kakao.com/v2/search/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var searchService : SearchRequestInterface = searchRetrofit.create(SearchRequestInterface::class.java)
}