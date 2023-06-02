package org.android.go.sopt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.android.go.sopt.util.UserSharedPreferences
import timber.log.Timber

@HiltAndroidApp
class SoptApplication : Application() {
    override fun onCreate() {
        prefs = UserSharedPreferences(this)
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    companion object {
        lateinit var prefs: UserSharedPreferences
    }
}
