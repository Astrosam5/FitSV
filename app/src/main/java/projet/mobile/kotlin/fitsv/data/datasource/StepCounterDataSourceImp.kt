/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.data.datasource

import kotlinx.coroutines.flow.Flow
import projet.mobile.kotlin.fitsv.data.source.local.StepCounterDao
import projet.mobile.kotlin.fitsv.data.source.remote.StepCounterApi
import projet.mobile.kotlin.fitsv.domain.model.StepCounterModel
import javax.inject.Inject

/**
 * Class UserDataSourceImp
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
class StepCounterDataSourceImp @Inject constructor(
    private val stepCounterApi: StepCounterApi,
    private val stepCounterDao: StepCounterDao
) : StepCounterDataSource {

    override suspend fun getStepsCounterFromDB(): Flow<List<StepCounterModel>> {
        return stepCounterDao.getAllStepCounter()
    }
}