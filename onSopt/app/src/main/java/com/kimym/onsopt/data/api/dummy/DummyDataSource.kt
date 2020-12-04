package com.kimym.onsopt.data.api.dummy

import com.kimym.onsopt.data.model.DummyUsers

interface DummyDataSource {
    suspend fun getDummyUsers(page : Int) : DummyUsers
}