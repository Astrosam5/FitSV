/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import projet.mobile.kotlin.fitsv.FitSVApplication
import projet.mobile.kotlin.fitsv.domain.model.UserModel
import projet.mobile.kotlin.fitsv.domain.repository.UserRepository
import projet.mobile.kotlin.fitsv.ui.states.LoginState
import projet.mobile.kotlin.fitsv.ui.states.ResourcesState
import javax.inject.Inject

/**
 * Class LoginViewModel
 * TODO Comment use case of class LoginViewModel
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    companion object {
        const val TAG = "LoginViewModel"
    }

    private val _userList : MutableStateFlow<ResourcesState<List<UserModel>>> =
        MutableStateFlow(ResourcesState.Loading())
    val userList: StateFlow<ResourcesState<List<UserModel>>> = _userList

    init {
        getAllUsers()
    }

    private fun getAllUsers() {
        viewModelScope.launch (Dispatchers.IO) {
            userRepository.getAllUsers()
                .collectLatest { userList ->
                    _userList.value = userList
                }
        }
    }

    fun saveUser(user: UserModel) {
        viewModelScope.launch {
            userRepository.insertUserOnDB(user)
        }
    }

    fun deleteAllUser() {
        viewModelScope.launch {
            userRepository.deleteAllUserFromDB()
        }
    }


    fun connexion(
        username: String,
        password: String,
        listUser: List<UserModel>,
        onNavigateToHomeScreen: () -> Unit
    ) {
        // Username transformation
        val cleanUsername = username.lowercase().trim()
        // Search user in list
        var userFound: UserModel? = null
        for (user in listUser) {
            if (user.name.lowercase() == cleanUsername) {
                userFound = user
            }
        }
        // Password transformation
        val cleanPassword = password.trim()

        if (userFound != null) {
            if (userFound.password == cleanPassword) {
                FitSVApplication.loginState = LoginState(userFound, true)
                val homeText = "Hello ${FitSVApplication.loginState.user?.name ?: ""}"
                FitSVApplication.homeScreenText = homeText
                onNavigateToHomeScreen()
            } else {
                // TODO Prompt password is wrong
            }
        } else {
            // TODO Prompt error message
        }
    }


}