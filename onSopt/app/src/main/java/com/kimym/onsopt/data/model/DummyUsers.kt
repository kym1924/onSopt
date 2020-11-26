package com.kimym.onsopt.data.model

data class DummyUsers(
    val page: Int,
    val total_pages: Int,
    val data: List<DummyUserInfo>
)
data class DummyUserInfo(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
)