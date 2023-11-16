/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.data.source.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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

    @Insert(onConflict = OnConflictStrategy.REPLACE) // TODO check if best strategy
    fun insertUser(userModel: UserModel)


}