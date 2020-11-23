package com.kimym.onsopt.data.model

data class ResponseUser(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : RequestSignUp
)