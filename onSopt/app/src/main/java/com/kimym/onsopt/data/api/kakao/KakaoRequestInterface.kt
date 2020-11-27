package com.kimym.onsopt.data.api.kakao

import com.kimym.onsopt.data.model.KakaoWebData
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KakaoRequestInterface {
    @Headers("Authorization: KakaoAK {REST-API KEY}}")
    @GET("web")
    suspend fun getKakaoWebSearch(
        @Query("query") query : String,
        @Query("page") page : Int
    ) : KakaoWebData
}

