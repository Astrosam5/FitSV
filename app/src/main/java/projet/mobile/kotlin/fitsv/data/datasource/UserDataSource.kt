/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.data.datasource

import projet.mobile.kotlin.fitsv.domain.model.UserModel
import retrofit2.Response

/**
 * Interface UserDataSource
 * TODO Comment use case of interface UserDataSource
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
interface UserDataSource {
    suspend fun getAllUsers(): Response<List<UserModel>>

    suspend fun saveUser(user: UserModel)

    suspend fun deleteAllUser()
}