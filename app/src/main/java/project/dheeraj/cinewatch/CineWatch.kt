package project.dheeraj.cinewatch

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber



@HiltAndroidApp
class CineWatch : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}