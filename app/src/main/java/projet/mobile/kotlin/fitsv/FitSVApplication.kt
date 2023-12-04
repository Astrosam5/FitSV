/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv

import android.annotation.SuppressLint
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
class FitSVApplication : Application()  {

//    @Inject
//    lateinit var workerFactory: HiltWorkerFactory
//
//    @SuppressLint("RestrictedApi")
//    override fun getWorkManagerConfiguration() =
//        Configuration.Builder()
//            .setWorkerFactory(workerFactory).build()

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Arrive inside OnCreate")
    }

    companion object {
        const val TAG = "FitSVApplication"
        var loginState: LoginState = LoginState()
        var homeScreenText: String = "HOME"

    }
}