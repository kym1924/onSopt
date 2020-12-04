package com.kimym.onsopt.data.api.search

import javax.inject.Inject

class SearchDataSourceImpl @Inject constructor(
    private val searchRequestInterface : SearchRequestInterface
) : SearchDataSource {
    override suspend fun getWebSearch(query : String, page : Int) = searchRequestInterface.getWebSearch(query, page)
}