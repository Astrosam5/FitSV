/*
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv

//import projet.mobile.kotlin.fitsv.data.sensors.HardwareStepCounterSource
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.work.Constraints
import dagger.hilt.android.AndroidEntryPoint
import projet.mobile.kotlin.fitsv.ui.theme.BottomNavBarDemoTheme
import projet.mobile.kotlin.fitsv.ui.util.Navigation
import projet.mobile.kotlin.fitsv.ui.util.rememberWindowSize

/**
 * Class MainActivity
 * Used setup the UI of the main screen of the app
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     * Function used to define what will be created at setup
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavBarDemoTheme {
                val window = rememberWindowSize()
                Navigation(windowSize = window)

            }
        }
    }

    override fun onResume() {
        super.onResume()

        val constraint = Constraints.Builder()
            .build()

//        val stepWorkerRequest = OneTimeWorkRequestBuilder<HardwareStepCounterSource>()
//            .setConstraints(constraint)
//            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST) // Lance service tout de suite
//            .build()
//
//        WorkManager.getInstance(applicationContext)
//            .enqueueUniqueWork("step_counter",
//                ExistingWorkPolicy.KEEP,
//                stepWorkerRequest)

//        val workRequest = OneTimeWorkRequestBuilder<HardwareStepCounterSource>().build()
//        WorkManager.getInstance(applicationContext).enqueue(workRequest)
    }

}



