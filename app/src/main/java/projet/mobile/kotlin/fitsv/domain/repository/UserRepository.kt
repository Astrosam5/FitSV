/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import projet.mobile.kotlin.fitsv.data.datasource.UserDataSource
import projet.mobile.kotlin.fitsv.domain.model.UserModel
import projet.mobile.kotlin.fitsv.ui.states.ResourcesState
import javax.inject.Inject

/**
 * Interface MyRepository
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
class UserRepository @Inject constructor(
    private val userDataSource: UserDataSource
) {

    suspend fun getAllUsers(): Flow<ResourcesState<List<UserModel>>> {
        return flow {
            emit(ResourcesState.Loading())
            val response = userDataSource.getAllUsers()
            if (response.isSuccessful && response.body() != null) {
                emit(ResourcesState.Success(response.body()!!))
            } else {
                emit(ResourcesState.Error("Error fetching Users"))
            }
        }.catch { e ->
            emit(ResourcesState.Error(e.localizedMessage ?:"Some error in flow"))
        }
    }

    suspend fun insertUserOnDB(user: UserModel) {
        return userDataSource.insertUserOnDB(user)
    }

    suspend fun deleteAllUserFromDB() {
        return userDataSource.deleteAllUserFromDB()
    }

    suspend fun addUserAPI(user: UserModel) {
        return userDataSource.addUserAPI(user)
    }


}