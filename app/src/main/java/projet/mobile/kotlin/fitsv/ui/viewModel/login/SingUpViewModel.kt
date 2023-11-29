/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.ui.viewModel.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import projet.mobile.kotlin.fitsv.domain.model.UserModel
import projet.mobile.kotlin.fitsv.domain.repository.UserRepository
import projet.mobile.kotlin.fitsv.ui.states.ResourcesState
import javax.inject.Inject

/**
 * Class SingupViewModel
 * TODO Comment use case of class SingupViewModel
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
@HiltViewModel
class SingUpViewModel@Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

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

    fun saveUserToDB(user: UserModel) {
        viewModelScope.launch {
            userRepository.insertUserOnDB(user)
        }
    }

    fun addUserOnAPI(user: UserModel) {
        viewModelScope.launch {
            userRepository.addUserAPI(user)
        }
    }

    /**
     * Check if password is ok
     */
    fun doesPasswordFormatIsOk(password: String): Boolean {
        return password.length > 8 && password.matches(Regex("[\\w*+:/$&?@!-]+"))
    }

    /**
     * Check if username format is ok
     */
    fun doesUsernameFormatIsOk(username: String): Boolean {
        return username.matches(Regex("\\w+"))
    }

    /**
     * return true if password are the same, else false
     */
    fun doesPasswordCorrespond(password: String, passwordConf: String): Boolean {
        return password.trim() == passwordConf.trim()
    }

    /**
     * @return true if user exist else false
     */
    fun doesUsernameExist(username: String, listUser: List<UserModel>) : Boolean {
        val cleanUsername = username.lowercase().trim()
        // Search user in list
        var userFound: UserModel? = null
        for (user in listUser) {
            if (user.name.lowercase() == cleanUsername) {
                userFound = user
            }
        }
        return userFound != null
    }

}