package com.kimym.onsopt.data.api.user

import com.kimym.onsopt.data.model.RequestSignIn
import com.kimym.onsopt.data.model.RequestSignUp

class UserRepository(private val requestInterface : UserRequestInterface) {

    suspend fun requestSignIn(body : RequestSignIn) = requestInterface.signIn(body)

    suspend fun requestSignUp(body : RequestSignUp) = requestInterface.signUp(body)
}
