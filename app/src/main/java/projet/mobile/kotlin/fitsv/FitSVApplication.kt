/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv

import android.app.Application
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import projet.mobile.kotlin.fitsv.ui.states.LoginState
import javax.inject.Inject

/**
 * Class FitSVApplication
 * TODO Comment use case of class FitSVApplication
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */

@HiltAndroidApp
class FitSVApplication : Application(), Configuration.Provider  {

    @Inject
    lateinit var hiltWorkerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Arrive inside OnCreate")
    }

    companion object {
        const val TAG = "FitSVApplication"
        var loginState: LoginState = LoginState()
        var homeScreenText: String = "HOME"

    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(hiltWorkerFactory)
            .build()
    }
}