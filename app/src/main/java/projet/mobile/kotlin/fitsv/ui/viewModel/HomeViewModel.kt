/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.ui.viewModel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import projet.mobile.kotlin.fitsv.data.sensors.HardwareStepCounterSource
import projet.mobile.kotlin.fitsv.data.sensors.LightSensor
import projet.mobile.kotlin.fitsv.data.source.local.UserDao
import javax.inject.Inject

/**
 * Class HomeViewModel
 * TODO Comment use case of class HomeViewModel
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val lightSensor: LightSensor,
    private val userDao: UserDao
): ViewModel() {


    /**
     * Run the worker once time.
     */
    fun addStepOnceWorker(context: Context) {
        val workRequest = OneTimeWorkRequestBuilder<HardwareStepCounterSource>().build()
        WorkManager.getInstance(context).enqueue(workRequest)
    }
    

    var isDark by mutableStateOf(false)

    init {
        lightSensor.startListening()
        lightSensor.setOnSensorValuesChangedListener { values ->
            val lux = values[0]
            isDark = lux < 600f
        }

    }
}
