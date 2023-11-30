/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.data.datasource

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import projet.mobile.kotlin.fitsv.domain.model.StepCounterModel

/**
 * Interface StepCounterDataSource
 * TODO Comment use case of class StepCounterDataSource
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
interface StepCounterDataSource {
  suspend fun getStepsCounterFromDB(): Flow<List<StepCounterModel>>
}