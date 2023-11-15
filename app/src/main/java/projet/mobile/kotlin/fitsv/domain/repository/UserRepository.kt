/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.domain.repository

import projet.mobile.kotlin.fitsv.data.User

/**
 * Interface MyRepository
 * TODO Comment use case of interface MyRepository
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
interface UserRepository {
    suspend fun newUser(id: Int, name: String, location: String, title: String)

    suspend fun getUser(id: Int): User

    suspend fun updateUser(oldId: Int, newName: String, newLocation: String, newTitle: String)

    suspend fun deleteUser(id: Int)

    suspend fun getAllUsers(): List<User>
}