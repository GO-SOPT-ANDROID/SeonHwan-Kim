package org.android.go.sopt.util

import android.content.Context
import android.content.SharedPreferences
import org.android.go.sopt.data.User

class UserSharedPreferences(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(KEY_PREFS, 0)

    fun getString(key: String, defValue: String?): String {
        return prefs.getString(key, defValue).toString()
    }

    fun setString(key: String, value: String?) {
        prefs.edit().putString(key, value).apply()
    }

    fun getBoolean(key: String, defValue: Boolean): Boolean {
        return prefs.getBoolean(key, defValue)
    }

    fun setBoolean(key: String, value: Boolean) {
        prefs.edit().putBoolean(key, value).apply()
    }

    fun saveUserInformation(
        isLogin: Boolean,
        user: User
    ) {
        setBoolean(KEY_ISLOGIN, isLogin)
        setString(KEY_ID, user.id)
        setString(KEY_PASSWORD, user.password)
        setString(KEY_NAME, user.name)
        setString(KEY_SPECIALTY, user.specialty)
    }

    fun deleteUserInformation() = prefs.edit().clear().apply()

    companion object {
        private const val KEY_PREFS = "userInfo"
        private const val KEY_ISLOGIN = "isLogin"
        private const val KEY_ID = "id"
        private const val KEY_PASSWORD = "password"
        private const val KEY_NAME = "name"
        private const val KEY_SPECIALTY = "specialty"
    }
}