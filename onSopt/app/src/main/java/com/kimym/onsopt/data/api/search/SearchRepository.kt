package com.kimym.onsopt.data.api.search

import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val searchDataSource : SearchDataSource
) {
    suspend fun getWebSearch(query : String, page : Int) = searchDataSource.getWebSearch(query, page)
}