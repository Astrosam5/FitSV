/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.ui.viewModel

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import projet.mobile.kotlin.fitsv.FitSVApplication
import projet.mobile.kotlin.fitsv.FitSVApplication.Companion.homeScreenText
import projet.mobile.kotlin.fitsv.FitSVApplication.Companion.loginState
import projet.mobile.kotlin.fitsv.domain.model.UserModel
import projet.mobile.kotlin.fitsv.domain.repository.UserRepository
import projet.mobile.kotlin.fitsv.ui.routes.BottomBarRoutes
import projet.mobile.kotlin.fitsv.ui.screens.HomeScreen
import projet.mobile.kotlin.fitsv.ui.states.LoginState
import projet.mobile.kotlin.fitsv.ui.states.ResourcesState
import projet.mobile.kotlin.fitsv.ui.util.rememberWindowSize
import retrofit2.Response
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

    fun getAllUsers() {
        viewModelScope.launch (Dispatchers.IO) {
            userRepository.getAllUsers()
                .collectLatest { userList ->
                    _userList.value = userList
                }
        }
    }

}