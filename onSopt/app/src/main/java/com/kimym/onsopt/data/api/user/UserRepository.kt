package com.kimym.onsopt.data.api.user

import android.content.SharedPreferences
import com.kimym.onsopt.data.model.RequestSignIn
import com.kimym.onsopt.data.model.RequestSignUp
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDataSource : UserDataSource,
    private val sharedPreferences : SharedPreferences
) {
    suspend fun requestSignIn(body : RequestSignIn) = userDataSource.signIn(body)

    suspend fun requestSignUp(body : RequestSignUp) = userDataSource.signUp(body)

    fun getEmail() = sharedPreferences.getString("email", "")

    fun getPassword() = sharedPreferences.getString("password", "")

    fun putEmail(id : String) {
        sharedPreferences.edit().putString("email", id).apply()
    }

    fun putPassword(password : String) {
        sharedPreferences.edit().putString("password", password).apply()
    }

    fun logout() {
        sharedPreferences.edit().clear().apply()
    }
}
