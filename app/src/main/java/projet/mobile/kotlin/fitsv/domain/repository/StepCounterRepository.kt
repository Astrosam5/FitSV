/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.domain.repository

import kotlinx.coroutines.flow.Flow
import projet.mobile.kotlin.fitsv.data.datasource.StepCounterDataSource
import projet.mobile.kotlin.fitsv.domain.model.StepCounterModel
import javax.inject.Inject

/**
 * Interface MyRepository
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
class StepCounterRepository @Inject constructor(
    private val stepCounterDataSource: StepCounterDataSource
) {

    suspend fun getStepsCounterFromDB(): Flow<List<StepCounterModel>> {
        return stepCounterDataSource.getStepsCounterFromDB()
    }


}