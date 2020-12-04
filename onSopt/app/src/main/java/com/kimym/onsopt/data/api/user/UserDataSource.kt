package com.kimym.onsopt.data.api.user

import com.kimym.onsopt.data.model.RequestSignIn
import com.kimym.onsopt.data.model.RequestSignUp
import com.kimym.onsopt.data.model.ResponseUser

interface UserDataSource {
    suspend fun signIn(body : RequestSignIn) : ResponseUser

    suspend fun signUp(body : RequestSignUp) : ResponseUser
}