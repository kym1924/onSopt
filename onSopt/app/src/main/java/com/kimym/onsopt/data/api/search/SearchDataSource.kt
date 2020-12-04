package com.kimym.onsopt.data.api.search

import com.kimym.onsopt.data.model.WebData

interface SearchDataSource {
    suspend fun getWebSearch(query : String, page : Int) : WebData
}