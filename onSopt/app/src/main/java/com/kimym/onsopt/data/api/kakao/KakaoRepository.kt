package com.kimym.onsopt.data.api.kakao

class KakaoRepository(private val requestInterface : KakaoRequestInterface) {

    suspend fun getKakaoWebSearch(query : String) = requestInterface.getKakaoWebSearch(query)
}