/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.data.sensors

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

/**
 * Class HardwareStepCounterSource
 * TODO Comment use case of class HardwareStepCounterSource
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
class HardwareStepCounterSource (
    params: WorkerParameters,
    private val context: Context,
    stepSensor: StepSensor
): CoroutineWorker(context, params) {

    private var numberOfStep = 0
    private var baseStepNumber: Int? = null
    override suspend fun doWork(): Result {
        TODO("Not yet implemented")
    }
}