/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.data.repository

import android.app.Application
import projet.mobile.kotlin.fitsv.R
import projet.mobile.kotlin.fitsv.data.User
import projet.mobile.kotlin.fitsv.data.remote.UserApi
import projet.mobile.kotlin.fitsv.domain.repository.UserRepository

/**
 * Class MyRepositoryImplementation
 * TODO Comment use case of class MyRepositoryImplementation
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
class UserRepositoryImpl(
    private val api: UserApi,
    private val appContext: Application
): UserRepository {

    init {
        val appName = appContext.getString(R.string.app_name)
        println("Hello from the user. The app Name is $appName")
    }

    override suspend fun newUser(id: Int, name: String, location: String, title: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(id: Int): User {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(
        oldId: Int,
        newName: String,
        newLocation: String,
        newTitle: String
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllUsers(): List<User> {
        TODO("Not yet implemented")
    }
}