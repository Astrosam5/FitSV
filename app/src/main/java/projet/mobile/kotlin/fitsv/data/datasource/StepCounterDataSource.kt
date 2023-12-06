/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.data.datasource

import kotlinx.coroutines.flow.Flow
import projet.mobile.kotlin.fitsv.domain.model.StepCounterModel

/**
 * Interface StepCounterDataSource
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
interface StepCounterDataSource {
  suspend fun getStepsCounterFromDB(): Flow<List<StepCounterModel>>
}