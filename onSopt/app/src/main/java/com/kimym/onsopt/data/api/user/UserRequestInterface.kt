package com.kimym.onsopt.data.api.user

import com.kimym.onsopt.data.model.RequestSignIn
import com.kimym.onsopt.data.model.RequestSignUp
import com.kimym.onsopt.data.model.ResponseUser
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserRequestInterface {
    @Headers("Content-Type:application/json")
    @POST("/users/signin")
    suspend fun signIn(
        @Body body : RequestSignIn
    ) : ResponseUser

    @Headers("Content-Type:application/json")
    @POST("/users/signup")
    suspend fun signUp(
        @Body body : RequestSignUp
    ) : ResponseUser
}