/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import projet.mobile.kotlin.fitsv.domain.model.StepCounterModel
import projet.mobile.kotlin.fitsv.domain.model.UserModel

/**
 * Interface UserDao
 * TODO Comment use case of interface UserDao
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
@Dao
interface StepCounterDao {

    @Query("SELECT * FROM stepCounter")
    fun getAllStepCounter(): Flow<List<StepCounterModel>>

    @Insert
    suspend fun insert(steps: StepCounterModel)
}