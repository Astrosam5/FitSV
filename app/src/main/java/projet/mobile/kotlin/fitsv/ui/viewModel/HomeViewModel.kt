/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.ui.viewModel

//import projet.mobile.kotlin.fitsv.data.sensors.HardwareStepCounterSource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import projet.mobile.kotlin.fitsv.data.sensors.LightSensor
import javax.inject.Inject

/**
 * Class HomeViewModel
 * TODO Comment use case of class HomeViewModel
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    lightSensor: LightSensor,
): ViewModel() {


    var isDark by mutableStateOf(false)

    init {
        lightSensor.startListening()
        lightSensor.setOnSensorValuesChangedListener { values ->
            val lux = values[0]
            isDark = lux < 600f
        }

    }
}
