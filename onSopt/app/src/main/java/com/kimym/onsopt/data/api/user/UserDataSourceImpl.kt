package com.kimym.onsopt.data.api.user

import com.kimym.onsopt.data.model.RequestSignIn
import com.kimym.onsopt.data.model.RequestSignUp
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userRequestInterface : UserRequestInterface
) : UserDataSource {
    override suspend fun signIn(body : RequestSignIn) = userRequestInterface.signIn(body)

    override suspend fun signUp(body : RequestSignUp) = userRequestInterface.signUp(body)
}