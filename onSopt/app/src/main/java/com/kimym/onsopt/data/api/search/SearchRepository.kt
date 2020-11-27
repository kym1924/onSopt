package com.kimym.onsopt.data.api.search

class SearchRepository(private val requestInterface : SearchRequestInterface) {

    suspend fun getWebSearch(query : String, page : Int) = requestInterface.getWebSearch(query, page)
}