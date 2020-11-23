package com.kimym.onsopt.data.api

import com.kimym.onsopt.data.model.RequestSignIn
import com.kimym.onsopt.data.model.RequestSignUp
import com.kimym.onsopt.data.model.ResponseUser
import retrofit2.http.Body
import retrofit2.http.POST

interface UserRequestInterface {
    @POST("/users/signin")
    suspend fun signIn(
        @Body body : RequestSignIn
    ) : ResponseUser

    @POST("/users/signup")
    suspend fun signUp(
        @Body body : RequestSignUp
    ) : ResponseUser
}