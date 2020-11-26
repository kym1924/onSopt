package com.kimym.onsopt.data.api.kakao

import com.kimym.onsopt.data.model.KakaoWebData
import retrofit2.http.GET
import retrofit2.http.Query

interface KakaoRequestInterface {
    @GET("web")
    suspend fun getKakaoWebSearch(
        @Query("query") query : String
    ) : KakaoWebData
}

