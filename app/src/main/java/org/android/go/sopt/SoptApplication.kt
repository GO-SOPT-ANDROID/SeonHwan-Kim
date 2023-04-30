package org.android.go.sopt

import android.app.Application
import org.android.go.sopt.util.UserSharedPreferences

class SoptApplication : Application() {
    override fun onCreate() {
        prefs = UserSharedPreferences(this)
        super.onCreate()
    }

    companion object {
        lateinit var prefs: UserSharedPreferences
    }
}