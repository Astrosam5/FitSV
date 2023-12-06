/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.data.datasource

import projet.mobile.kotlin.fitsv.domain.model.UserModel
import retrofit2.Response

/**
 * Interface UserDataSource
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
interface UserDataSource {
    suspend fun getAllUsers(): Response<List<UserModel>>

    suspend fun insertUserOnDB(user: UserModel)

    suspend fun deleteAllUserFromDB()
    suspend fun addUserAPI(user: UserModel)

}