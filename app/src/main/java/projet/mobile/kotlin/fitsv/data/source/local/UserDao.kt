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
import projet.mobile.kotlin.fitsv.domain.model.UserModel

/**
 * Interface UserDao
 * TODO Comment use case of interface UserDao
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAllUser(): LiveData<List<UserModel>>

    @Insert
    fun insertUser(vararg users: UserModel)

    @Delete
    fun delete(user: UserModel)

    @Query("DELETE FROM user")
    fun deleteAllUser()

    @Query("SELECT steps FROM user")
    fun getUserSteps(): Flow<Int>


}