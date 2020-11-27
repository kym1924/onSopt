package com.kimym.onsopt.data.api.kakao

class KakaoRepository(private val requestInterface : KakaoRequestInterface) {

    suspend fun getKakaoWebSearch(query : String, page : Int) = requestInterface.getKakaoWebSearch(query, page)
}