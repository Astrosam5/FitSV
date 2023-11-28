/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.data.datasource

import projet.mobile.kotlin.fitsv.data.source.local.UserDao
import projet.mobile.kotlin.fitsv.data.source.remote.UserApi
import projet.mobile.kotlin.fitsv.domain.model.UserModel
import retrofit2.Response
import javax.inject.Inject

/**
 * Class UserDataSourceImp
 * TODO Comment use case of class UserDataSourceImp
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
class UserDataSourceImp @Inject constructor(
    private val userApi: UserApi,
    private val userDao: UserDao
) : UserDataSource {
    override suspend fun getAllUsers(): Response<List<UserModel>> {
        return userApi.getAllUsers()
    }

    override suspend fun insertUserOnDB(user: UserModel) {
        return userDao.insertUser(user)
    }

    override suspend fun deleteAllUserFromDB() {
        return userDao.deleteAllUser()
    }

    override suspend fun addUserAPI(user: UserModel) {
        return userApi.newUser(user)
    }
}