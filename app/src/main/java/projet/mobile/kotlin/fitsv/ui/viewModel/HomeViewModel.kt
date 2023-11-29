/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import projet.mobile.kotlin.fitsv.data.sensors.LightSensor
import projet.mobile.kotlin.fitsv.data.sensors.MeasurableSensor
import projet.mobile.kotlin.fitsv.data.sensors.StepSensor
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
    private val stepSensor: StepSensor
): ViewModel() {

    var isDark by mutableStateOf(false)
    var nbSteps by mutableIntStateOf(0)

    init {
        lightSensor.startListening()
        lightSensor.setOnSensorValuesChangedListener { values ->
            val lux = values[0]
            isDark = lux < 600f
        }

        stepSensor.startListening()
        stepSensor.setOnSensorValuesChangedListener { values ->
            val steps = values[0]
            nbSteps = steps.toInt()
        }
    }
}
